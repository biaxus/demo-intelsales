(function($) {
	var $messageContainer = $(messageContainer), $formContainer = $(formContainer), controller = {
		init : function() {
			$formContainer.on('click', '#edit', function(e) {
				var $this = $(this);
				var state = $this.data("state");
				if (state === 'edit') {
					controller.edit();
				} else if (state === 'save') {
					controller.save();
				}
			});
			$listContainer.on('click', 'button[name="delete"]',
					controller.deleteEnterprise);
			$("#edit").data("state", "edit");
		},
		reinit : function() {
			$messageContainer = $("#messageContainer");
			$name = $("#name");
			$lastName = $("#lastName");
		},
		save : function() {
			var data = {
				name : $name.val(),
				lastName : $lastName.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			};
			$.post('/fn/profile', data, success).fail(fail);
		},
		edit : function() {
			$name.attr("disabled",undefined);
			$lastName.attr("disabled",undefined);
			$("#edit").data("state", "save");
		},
		cancel : function(e) {
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
			$("#edit").data("state", "edit");
		}
	};
	controller.init();
})(jQuery);