(function($) {
	var $messageContainer=$(messageContainer),$formContainer = $(formContainer), $listContainer = $(listContainer), controller = {
		init : function() {
			$formContainer.on('submit', '#enterpriseForm', function(e) {
				e.preventDefault();
				controller.save();
			});
			$listContainer.on('click', 'button[name="delete"]',
					controller.deleteEnterprise);
		},
		save : function() {
			var data = {
				description : $("#description").val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			};
			$.post('/fn/enterprise', data, success).fail(fail);
		},
		reloadList : function() {
			var success = function(data, textStatus, jqXHR) {
				$listContainer.html(data);
			}, fail = function(response) {
				alert("error");
			};
			$.get('/fn/enterprise/list', success).fail(fail);
		},
		deleteEnterprise : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$messageContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			};
			
			var id = $(this).attr("data");
			$.ajax('/fn/enterprise/' + id, {
				method : 'DELETE',
				"success" : success
			}).fail(fail);
		}
	};
	controller.init();
})(jQuery);