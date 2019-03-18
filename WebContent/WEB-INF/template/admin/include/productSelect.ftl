<#--线路表格-->
<div class="panel-body">
	<script type="text/javascript">
	jQuery(document).ready(function($)
		{
			$("#example-3").dataTable({
			    'bStateSave': true,
			    'bLengthChange': true,
			    'bFilter':false,
			    'bPaginate': false,
			    'bInfo': false,
			    'bSort': false,
			    //'sScrollX': "100%",
				//'sScrollXInner': "101%",
				//'bScrollCollapse': true,
				//'sScrollY': 800,//竖向滚动条 tbody区域的高度    
				
				aLengthMenu: [
					[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
				]
			});
			// Replace checkboxes when they appear
			var $state = $("#example-3 thead input[type='checkbox']");
			
			$("#example-3").on('draw.dt', function()
			{
				cbr_replace();
				
				$state.trigger('change');
			});
			
			// Script to select all checkboxes
			$state.on('change', function(ev)
			{
				var $chcks = $("#example-3 tbody input[type='checkbox']");
				
				if($state.is(':checked'))
				{
					$chcks.prop('checked', true).trigger('change');
				}
				else
				{
					$chcks.prop('checked', false).trigger('change');
				}
			});
		
		});
	</script>					
	<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
		<thead>
			<tr>
			    <th class="no-sorting">
					<input type="checkbox" class="cbr">
				</th>
				<th>线路编号</th>
				<th>名称</th>
			</tr>
		</thead>															
		<tbody>
			<#list productList as product>
			<tr>
			    <th class="no-sorting">
					<input type="checkbox" class="cbr" name="eachCheckbox" value="${(product.id)!}" tourCode="${(product.code)!}" tourName="${(product.name)!}">
				</th>
				<td>
					${(product.code)!} 
				</td>
				<td> 
					${(product.name)!} 
				</td>
			</tr>	
			</#list>
		</tbody>
	</table>					
</div>