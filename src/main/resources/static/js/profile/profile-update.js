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
			$formContainer.on('click', '#cancel', function(e) {
				controller.cancel();
			});
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
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/profile', data, success).fail(fail).always(always);
		},
		edit : function() {
			var data = {
				name : $name.val(),
				lastName : $lastName.val()
			},success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/profile/edit',data, success).fail(fail).always(always);
		},
		cancel : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			$.ajax('/fn/profile/cancel', {
				method : 'GET',
				"success" : success
			}).fail(fail).always(always);
			$("#edit").data("state", "edit");
		}
	};
	controller.init();
	controller.reinit();
})(jQuery);