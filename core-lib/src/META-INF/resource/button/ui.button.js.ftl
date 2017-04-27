<#if xhrParameter != "" >
<script>
function ${onclick}() {
	xhrSendParameter(
		'${xhrUrl}',
		${xhrParameter},
		function(data) {
			${loadFunction}
		},
		${errorFunction}
	);
}
</script>
</#if>

<#if formId != "" >
<script>
function ${onclick}() {
	xhrSendForm(
		'${xhrUrl}',
		'${formId}', 
		function(data) {
			${loadFunction}
		},
		${errorFunction}
	);
}
</script>
</#if>