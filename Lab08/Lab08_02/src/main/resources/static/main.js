$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();

	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;
			});
		} else{
			checkbox.each(function(){
				this.checked = false;
			});
		}
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});

	$('#btnDeleteSelectedEmployees').click(function(e) {
		e.preventDefault();
		let ids = $('.checkbox:checked').map(function() {
			return $(this).val();
		}).get();
		if (ids.length == 0) {
			alert('Please select at least one employee to delete.');
			return;
		}

		console.log(ids)

		$.ajax({
			url: '/delete-selected-employees',
			type: 'POST',
			data: JSON.stringify(ids),
			contentType: "application/json",
			dataType: "json",
			success: function(data) {
				location.reload();
			},
			error: function(xhr, status, error) {
				console.log("Error: ", error);
			}
		});
	});
});

const btnDelete = document.getElementsByClassName('delete')
let _href = ''
for (let i = 0; i < btnDelete.length; i++) {
	btnDelete[i].onclick = function (event) {
		event.preventDefault()
		const  target = event.target
		const link = target.parentElement // tag a
		_href = link.href // href of a
		const row = link.parentElement.parentElement // tr
		const nameEmployee = row.children[1].innerText // td['name']

		$('#modalBodyMessage').text('Are you sure you want to delete ' + nameEmployee)
		$('#deleteEmployeeModal').modal('show')

		$('#deleteEmployeeModal').off('click')
		$('#modalFooterDelete').click(function (event) {
			event.preventDefault()
			$.ajax({
				url: _href,
				type: 'POST',
				data: {},
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (data) {
					$('#deleteEmployeeModal').modal('hide')
					location.reload()
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
			})
		})
	}
}