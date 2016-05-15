(function($) {
	var $messageContainer = $(messageContainer), $formContainer = $(formContainer), $listContainer = $(listContainer), controller = {
		init : function() {
			$formContainer.on('click', '#new', function(e) {
				var $this = $(this);
				var state = $this.data("state");
				if (state === 'new') {
					//controller._new();
				} else if (state === 'save') {
					controller.save();
				}
			});
			$formContainer.on('click', '#cancel', function(e) {
				controller.cancel();
			});
			$listContainer.on('click', 'button[name="delete"]', controller.deleteClient);
			$listContainer.on('click', 'button[name="edit"]', controller.editClient);
			$("#new").data("state", "new");
			$(document).ready(function(){
				$(cityTable).DataTable();
			});
		},
		reinit : function() {
			$messageContainer = $("#messageContainer");
			$name = $("#name");
			$id = $("#id");
			$description = $("#description");
		},
		save : function() {
			var data = {
				id : $id.val(),
				name : $name.val(),
				description : $description.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/city', data, success).fail(fail).always(always);
		},
		_new : function() {
			var data = {
				id : $id.val(),
				name : $name.val(),
				description : $description.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/city/edit', data, success).fail(fail).always(always);
		},
		editClient : function(e) {
			var id = $(this).attr("data");
			$id.val(id);
			var data = {
				id : $id.val(),
				name : $name.val(),
				description : $description.val()
			}, success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit();
			};
			$.post('/fn/city/edit', data, success).fail(fail).always(always);
		},
		cancel : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$formContainer.html(data);
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			$.ajax('/fn/city/cancel', {
				method : 'GET',
				"success" : success
			}).fail(fail).always(always);
			$("#new").data("state", "new");
		},
		reloadList : function() {
			var success = function(data, textStatus, jqXHR) {
				$listContainer.html(data);
				$(cityTable).DataTable()
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			$.get('/fn/city/list', success).fail(fail).always(always);
		},
		deleteClient : function(e) {
			var success = function(data, textStatus, jqXHR) {
				$messageContainer.html(data);
				controller.reloadList();
			}, fail = function(response) {
				alert("error");
			}, always = function(response) {
				controller.reinit()
			};
			var id = $(this).attr("data");
			$.ajax('/fn/city/' + id, {
				method : 'DELETE',
				"success" : success
			}).fail(fail).always(always);
		}
	};
	controller.init();
	controller.reinit();
})(jQuery);