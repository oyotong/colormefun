<#list pojo?keys as itemKey>
	<#assign item = pojo[itemKey]>
	<#if itemKey?matches("^[a-z#].*$") && (pojo[itemKey].NameType == "ToMany" || pojo[itemKey].NameType == "Items")>
KeyProperty_${pojo[itemKey].Name}=${pojo[itemKey].ReferenceClass.Id}
Element_${pojo[itemKey].Name}=${entityPackage}.${pojo[itemKey].ReferenceClass.ClassName}
CreateIfNull_${pojo[itemKey].Name}=true
	</#if>
</#list>
