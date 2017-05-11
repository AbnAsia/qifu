<#if xhrParameter != "" >
<script>
function ${onclick}() {
	<#if xhrSendNoPleaseWait == "Y" >
	xhrSendParameterNoPleaseWait(
	</#if>
	<#if xhrSendNoPleaseWait != "Y" >
	xhrSendParameter(
	</#if>
		'${xhrUrl}',
		${xhrParameter},
		function(data, textStatus) {
			${loadFunction};
		},
		function(jqXHR, textStatus, errorThrown) {
			${errorFunction};
		}
		<#if xhrSendNoPleaseWait != "Y" >
		, '${selfPleaseWaitShow}'
		</#if>
	);
}
</script>
</#if>

<#if formId != "" >
<script>
function ${onclick}() {
	<#if xhrSendNoPleaseWait == "Y" >
	xhrSendFormNoPleaseWait(
	</#if>
	<#if xhrSendForm != "Y" >
	xhrSendForm(
	</#if>
		'${xhrUrl}',
		'${formId}', 
		function(data, textStatus) {
			${loadFunction};
		},
		function(jqXHR, textStatus, errorThrown) {
			${errorFunction};
		}
		<#if xhrSendNoPleaseWait != "Y" >
		, '${selfPleaseWaitShow}'
		</#if>		
	);
}
</script>
</#if>