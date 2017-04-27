<#if xhrParameter != "" >
<script>
function ${onclick}() {
	xhrSendParameter(
		'${xhrUrl}',
		${xhrParameter},
		function(data, textStatus) {
			${loadFunction};
		},
		function(jqXHR, textStatus, errorThrown) {
			${errorFunction};
		}
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
		function(data, textStatus) {
			${loadFunction};
		},
		function(jqXHR, textStatus, errorThrown) {
			${errorFunction};
		}
	);
}
</script>
</#if>