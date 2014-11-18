DELIMITER $$

USE `a0812133843`$$

DROP PROCEDURE IF EXISTS `monthly_report`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `monthly_report`()
monthly_report:
    BEGIN
	DECLARE _last_month INT;
	DECLARE _month INT;
	DECLARE _last_year INT;
	DECLARE _year INT;
	DECLARE _last_report_year INT;
	DECLARE _last_report_month INT;
	DECLARE _now_year INT;
	DECLARE _now_month INT;
	DECLARE _now DATETIME;
	DECLARE _temp_start_time DATETIME;
	DECLARE _temp_end_time DATETIME;
	
	-- field in the report
	DECLARE _case_total INT;
	DECLARE _order_total INT;
	DECLARE _ticket_total INT;
	DECLARE _price_total INT;
	DECLARE _coupon_total INT;
	
	DECLARE _real_case_total INT;
	DECLARE _real_order_total INT;
	DECLARE _real_ticket_total INT;
	DECLARE _real_price_total INT;
	DECLARE _real_coupon_total INT;
	
	DECLARE _status VARCHAR(10);
	
	SET _now = NOW();
	SET _now_year = YEAR(_now);
	SET _now_month = MONTH(_now);
	/* 1. get the last report date */
	SELECT t.year, t.month INTO _last_report_year, _last_report_month FROM mf_monthly_report t WHERE t.status='closed' ORDER BY t.year DESC, t.month DESC LIMIT 1;
	IF _last_report_month IS NOT NULL THEN
		IF _last_report_month = 12 THEN
			SET _last_report_year = _last_report_year + 1;
  			SET _last_report_month = 1;
  		ELSE
			SET _last_report_month = _last_report_month + 1;
		END IF;	
	
	/* set the first order's date for last report date */	
	ELSE
		SELECT YEAR(t.created_date), MONTH(t.created_date) INTO _last_report_year, _last_report_month FROM mf_order t WHERE t.status='closed' ORDER BY t.created_date LIMIT 1;
	END IF;
	IF _last_report_month IS NULL THEN
		LEAVE monthly_report;
	END IF;
  	
	/* 2. find out the pre-total
	set _case_total = 5;
	set _ticket_total = _case_total * 20;
	SET _order_total = _ticket_total;
	set _price_total = _ticket_total * 200.00;
	set _coupon_total = _price_total * 0.10;
	*/
	/* 3. find out the real total monthly*/
	SET _last_year = _now_year;
	SET _year = _last_report_year;
	WHILE _year <= _last_year DO
	BEGIN
		IF _last_report_year < _now_year THEN
			SET _last_month = 12;
		ELSE
			SET _last_month = _now_month;
		END IF;
		SET _month = _last_report_month;
		WHILE _month <= _last_month DO
		BEGIN
			SET _temp_start_time = CONVERT(CONCAT(_year,'-',_month,'-01 00:00:00'), DATETIME);
			SET _temp_end_time = DATE_ADD(CONCAT(_year,'-',_month+1,'-01 00:00:00'),INTERVAL -1 SECOND);
		
			-- _case_total, _ticket_total, _order_total
			SELECT  COUNT(*), SUM(ticket_number), SUM(ticket_number), SUM(ticket_number * ticket_price)
			 INTO _case_total, _ticket_total, _order_total, _price_total
			 FROM mf_case WHERE start_date BETWEEN _temp_start_time AND _temp_end_time;
			
			-- _coupon_total
			SELECT SUM(deduction) INTO _coupon_total FROM mf_coupon WHERE `deadline` >=_temp_start_time 
        		/*AND (status<>'used' or (status='used' and used_date BETWEEN _temp_start_time AND _temp_end_time ) )*/
        		AND STATUS<>'used' AND active='Y';
			IF _coupon_total IS NULL THEN
        			SET _coupon_total = 0;
      			END IF;
			-- _real_case_total
			SELECT COUNT(*) INTO _real_case_total FROM mf_case WHERE active='Y' AND start_date BETWEEN _temp_start_time AND _temp_end_time;
			-- _real_order_total
			SELECT COUNT(DISTINCT o.order_no) INTO _real_order_total FROM mf_order o 
			 INNER JOIN mf_order_detail od ON o.order_no = od.order_no
			 WHERE o.status = 'closed' AND created_date BETWEEN _temp_start_time AND _temp_end_time;
			 
			-- _real_order_total, _real_price_total, _real_ticket_total
			SELECT COUNT(DISTINCT o.order_no), SUM(od.qty), SUM(od.price * od.qty)
			 INTO _real_order_total, _real_ticket_total, _real_price_total
			 FROM mf_order o INNER JOIN mf_order_detail od ON o.order_no = od.order_no
			 WHERE o.status = 'closed' AND created_date BETWEEN _temp_start_time AND _temp_end_time;
			 
			-- _real_coupon_total
			SELECT SUM(c.deduction) INTO _real_coupon_total FROM mf_order o 
			 INNER JOIN mf_order_coupon oc ON o.order_no = oc.order_no 
			 INNER JOIN mf_coupon c ON oc.coupon_id = c.coupon_id
			 WHERE c.`status`<>'used' AND o.order_no IN (SELECT so.order_no FROM mf_order so INNER JOIN mf_order_detail sod
				ON so.order_no = sod.order_no WHERE so.status = 'closed' AND created_date BETWEEN _temp_start_time AND _temp_end_time);
			IF _real_coupon_total IS NULL THEN
				SET _real_coupon_total = 0;
			END IF;
			-- delete all 'open' report
			DELETE FROM mf_monthly_report WHERE `year` = _year AND `month` = _month AND `status`='open';
			
			-- insert into monthly report
			INSERT INTO `mf_monthly_report`
				    (`year`,
				     `month`,
				     `case_total`,
				     `order_total`,
				     `ticket_total`,
				     `price_total`,
				     `coupon_total`,
				     `real_case_total`,
				     `real_order_total`,
				     `real_ticket_total`,
				     `real_price_total`,
				     `real_coupon_total`,
				     `status`,
				     `comment`,
				     `created_id`,
				     `created_date`)
			VALUES (_year,
				_month,
				CASE WHEN _case_total IS NULL THEN 0 ELSE _case_total END,
				CASE WHEN _order_total IS NULL THEN 0 ELSE _order_total END,
				CASE WHEN _ticket_total IS NULL THEN 0 ELSE _ticket_total END,
				CASE WHEN _price_total IS NULL THEN 0 ELSE _price_total END,
				CASE WHEN _coupon_total IS NULL THEN 0 ELSE _coupon_total END,
				CASE WHEN _real_case_total IS NULL THEN 0 ELSE _real_case_total END,
				CASE WHEN _real_order_total IS NULL THEN 0 ELSE _real_order_total END,
				CASE WHEN _real_ticket_total IS NULL THEN 0 ELSE _real_ticket_total END,
				CASE WHEN _real_price_total IS NULL THEN 0 ELSE _real_price_total END,
				CASE WHEN _real_coupon_total IS NULL THEN 0 ELSE _real_coupon_total END,
				CASE WHEN _year=_now_year AND _month=_now_month THEN 'open' ELSE 'closed' END,
				'',
				-1,
				_now);
			
			SET _month = _month + 1;
		END;
		END WHILE;
		SET _year = _year + 1;
	END;
	END WHILE;
	
    END$$

DELIMITER ;