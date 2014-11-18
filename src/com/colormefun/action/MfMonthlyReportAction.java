package com.colormefun.action;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.colormefun.entity.MfOrder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfMonthlyReport;
import com.colormefun.service.MfMonthlyReportService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.util.ReportUtils;
import shop.common.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/report") })
@ParentPackage("my-default")
public class MfMonthlyReportAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfMonthlyReportService mfMonthlyReportService;
	private List<MfMonthlyReport> list;
	private MfMonthlyReport report;
	
	private String[] cids;
	
	private Object json;

	public MfMonthlyReportAction() {
	}

	@Autowired
	public MfMonthlyReportAction(MfMonthlyReportService mfMonthlyReportService) {
		this.mfMonthlyReportService = mfMonthlyReportService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/report/add.jsp"),
			@Result(name = "input", location = "/admin/report/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/report/list.jsp"),
			@Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.list = mfMonthlyReportService.findAllMfMonthlyReportWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/report/list.jsp"),
			@Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = mfMonthlyReportService.findAllMfMonthlyReportBySearchWithPage(report);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/report/edit.jsp"),
			@Result(name = "input", location = "/admin/report/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = report.getYear();
		}
		report = this.mfMonthlyReportService.getMfMonthlyReportById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/report/view.jsp"),
			@Result(name = "input", location = "/admin/report/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "report.year", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = report.getYear();
		}
		report = this.mfMonthlyReportService.getMfMonthlyReportById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfMonthlyReportService.deleteMfMonthlyReportByIds(cids);
		}else{
			mfMonthlyReportService.deleteMfMonthlyReport(report);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/report/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfMonthlyReportService.saveMfMonthlyReport(report);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/report/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "report.year", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "report.year", message = "You must enter a value for year(save).")})
	public String update() {
		this.mfMonthlyReportService.updateMfMonthlyReport(report);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfMonthlyReportService.findAllMfMonthlyReportByNameWithIdAndName("" + report.getYear());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/report/select.jsp"),
			@Result(name = "input", location = "/admin/report/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = mfMonthlyReportService.findAllMfMonthlyReportBySearchWithPage(report);
		return ActionSupport.SUCCESS;
	}
	/*/admin/report/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/report/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/report/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = report.getYear();
		}
		//this.list = mfMonthlyReportService.findAllMfMonthlyReportByIds(cids);
		this.json = mfMonthlyReportService.findAllMfMonthlyReportByIds(cids);
		return ActionSupport.SUCCESS;
	}

    /* ************* 报表操作 **************/

    @Action(value = "monthlyTradingStatistics", results = {
            @Result(name = "success", location = "/admin/report/list.jsp"),
            @Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String monthlyTradingStatistics() {
        if(null == report || StringUtils.isNull(report.getYear())){
            return null;
        }
        List<MfMonthlyReport> monthlyReport = mfMonthlyReportService.findMonthlyTradingStatistics(report);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reports",monthlyReport);

        HttpServletResponse response = getContext().getResponse();
        ReportUtils.exportExcel(map, "Monthly_trade_statistics.xlsx",
                "Monthly_trade_statistics("+report.getYear()+").xlsx",
                getContext().getRequest(), response);
        return null;
    }

    @Action(value = "monthlyTradingDetail", results = {
            @Result(name = "success", location = "/admin/report/list.jsp"),
            @Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String monthlyTradingDetail() {
        if(null == report || StringUtils.isNull(report.getYear()) || StringUtils.isNull(report.getMonth())){
            return null;
        }
        List<MfOrder> orders = mfMonthlyReportService.findMonthlyTradingDetail(report);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orders",orders);

        HttpServletResponse response = getContext().getResponse();
        ReportUtils.exportExcel(map, "Monthly_trading_detail.xlsx",
                "Monthly_trading_detail("+report.getYear()+"/"+report.getMonth()+").xlsx",
                getContext().getRequest(), response);
        return null;
    }
    @Action(value = "monthlyPerformance", results = {
            @Result(name = "success", location = "/admin/report/list.jsp"),
            @Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String monthlyPerformance() {
        if(null == report || StringUtils.isNull(report.getYear()) || StringUtils.isNull(report.getMonth())){
            return null;
        }
        MfMonthlyReport monthlyReport = mfMonthlyReportService.findMonthlyPerformance(report);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("report",monthlyReport);

        HttpServletResponse response = getContext().getResponse();
        ReportUtils.exportExcel(map, "Monthly_performance_report.xlsx",
                "Monthly_performance_report("+report.getYear()+"/"+report.getMonth()+").xlsx",
                getContext().getRequest(), response);
        return null;
    }
    @Action(value = "yearlyPerformance", results = {
            @Result(name = "success", location = "/admin/report/list.jsp"),
            @Result(name = "input", location = "/admin/report/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String yearlyPerformance() {
        if(null == report || StringUtils.isNull(report.getYear()) || StringUtils.isNull(report.getMonth())){
            return null;
        }
        MfMonthlyReport monthlyReport = mfMonthlyReportService.findYearlyPerformance(report);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("report",monthlyReport);

        HttpServletResponse response = getContext().getResponse();
        ReportUtils.exportExcel(map, "Yearly_performance_report.xlsx",
                "Yearly_performance_report("+report.getYear()+").xlsx",
                getContext().getRequest(), response);
        return null;
    }

    ///////////////////// Getter & Setter ///////////////////////

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public void setCids(String[] cids){
        this.cids = cids;
    }

    public String[] getCids(){
        return this.cids;
    }

    public List<MfMonthlyReport> getList() {
        return list;
    }

    public void setList(List<MfMonthlyReport> list) {
        this.list = list;
    }

	public MfMonthlyReport getReport() {
		return report;
	}

	public void setReport(MfMonthlyReport report) {
		this.report = report;
	}

}
