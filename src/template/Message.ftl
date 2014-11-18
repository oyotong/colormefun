
#############${""?left_pad(pojo.ClassName?length, "#")}###################
#      POJO [${pojo.ClassName}] JSP Message     #
#############${""?left_pad(pojo.ClassName?length, "#")}###################

pojo.${pojo.ClassName}.name=${pojo.ClassName}
pojo.${pojo.ClassName}.id=${pojo.Id}
<#list pojo?keys as itemKey>
	<#assign item = pojo[itemKey]>
	<#if itemKey?matches("^[a-z#].*$")>
pojo.${pojo.ClassName}.${pojo[itemKey].Name}=${pojo[itemKey].Name?cap_first}
		<#assign title = "">
		<#switch pojo[itemKey].NameType>
		  <#case "ToMany">
		     <#assign title = "${i18n('validation.message.items', pojo[itemKey].ReferenceClass.ClassName)}"/>
		     <#break>
		  <#case "ToOne">
		     <#assign title = "${i18n('validation.message.item', pojo[itemKey].ReferenceClass.ClassName)}"/>
		     <#break>
		  <#case "Unknown">
		     <#break>
		  <#default>
		     <#assign title = "${i18n('validation.message.valid', i18n('validation.type.${pojo[itemKey].NameType?lower_case}'))}"/>
		     <#break>
		</#switch>  
pojo.${pojo.ClassName}.${pojo[itemKey].Name}.title=${title}
	</#if>
</#list>

	