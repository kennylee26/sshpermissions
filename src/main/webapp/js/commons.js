function showQueryDialog(options){
	var opts = options || {};
	var dlg = $('#dlg-query');
	if (!dlg.length){
		dlg = $('<div id="dlg-query"></div>').appendTo('body');
		dlg.dialog({
			title:opts.title||'高级查询',
			width:opts.width||400,
			height:opts.height||300,
			closed:true,
			modal:true,
			href:opts.form,
			buttons:[{
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					dlg.dialog('close');
					var param = {};
					dlg.find('.query').each(function(){
						var name = $(this).attr('name');
						var val = $(this).val();
						if ($(this).hasClass('datebox-f')){
							name = $(this).attr('comboname');
							val = $(this).datebox('getValue');
						} else if ($(this).hasClass('combogrid-f')){
							name = $(this).attr('comboname');
							val = $(this).combogrid('getValue');
						} else if ($(this).hasClass('combobox-f')){
							name = $(this).attr('comboname');
							val = $(this).combobox('getValue');
						}
						param[name] = val;
					});
					opts.callback(param);
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){dlg.dialog('close');}
			}]
		});
	}
	dlg.dialog('open');
}

