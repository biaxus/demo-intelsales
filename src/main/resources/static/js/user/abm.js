(function($) {
	var $messageContainer = $(messageContainer), $formContainer = $(formContainer), $listContainer = $(listContainer), controller = {
		init : function() {
			$formContainer.on('click', '#new', function(e) {
				var $this = $(this);
				var state = $this.data("state");
				if (state === 'new') {
					controller._new();
				} else if (state === 'save') {
					controller.save();
				}
			});
			$formContainer.on('click', '#cancel', function(e) {
				controller.cancel();
			});
			$listContainer.on('click', 'button[name="delete"]', controller.deleteUser);
			$listContainer.on('click', 'button[name="edit"]', controller.editUser);
			$("#new").data("state", "new");
			$(document).ready(function(){
				$(userTable).DataTable();
			});
		},
		reinit : function() {
			$messageContainer = $("#messageContainer");
			$name = $("#name");
			$username = $("#username");
			$lastName = $("#lastName");
		},
		save : function() {
			var data = {
				username : $username.val(),
				name : $name.val(),
				lastName : $lastName.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/user', data, success).fail(fail).always(always);
		},
		_new : function() {
			var data = {
				username : $username.val(),
				name : $name.val(),
				lastName : $lastName.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/user/edit', data, success).fail(fail).always(always);
		},
		editUser : function(e) {
			var username = $(this).attr("data");
			$username.val(username);
			var data = {
				username : $username.val(),
				name : $name.val(),
				lastName : $lastName.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/user/edit', data, success).fail(fail).always(always);
		},
		cancel : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			$.ajax('/fn/user/cancel', {
				method : 'GET',
				"success" : success
			}).fail(fail).always(always);
			$("#new").data("state", "new");
		},
		reloadList : function() {
			var success = function(data, textStatus, jqXHR) {
				$listContainer.html(data);
				$(userTable).DataTable()
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			$.get('/fn/user/list', success).fail(fail).always(always);
		},
		deleteUser : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$messageContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			var username = $(this).attr("data");
			$.ajax('/fn/user/' + username, {
				method : 'DELETE',
				"success" : success
			}).fail(fail).always(always);
		}
	};
	controller.init();
	controller.reinit();
})(jQuery);