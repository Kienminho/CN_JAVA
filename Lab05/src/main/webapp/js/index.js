function addProduct() {
    //lấy form add product
    const formAddProduct = document.getElementById("from-add-product");
    const formData = new FormData(formAddProduct);
    //lấy dữ liệu đầu vào
    const name = $("#product-name").val();
    const price = $("#price").val();

    //lấy element hiển thị lỗi
    const message = $("#message");
    if(!vaildInputData(name, price)) {
        displayMessage(message, "Vui lòng nhập đủ thông tin sản phẩm.")
    }
    else {
        fetch(`/home/add-product`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(formData).toString(),
        })
            .then(res => res.json())
            .then(data => {
                if(data.statusCode === 200) {
                    location.reload()
                }
                else {
                    displayMessage(message, data.message);
                }
            })
            .catch(err => console.log(err))
    }


}


function vaildInputData(name, price) {
    if(name === "" || price ==="")
        return false;
    return  true;
}


//hiển thị thông báo lỗi
function displayMessage(containerMessage, message) {
    containerMessage.text(message);
    containerMessage.removeClass("d-none");
    setTimeout(() => {
        containerMessage.addClass("d-none");
    }, 3000)
}


//xác nhân xoá sản phẩm
function confirmDelete(productId) {

    //text xác nhận xoá sản phẩm

    $('.confirm-delete').html(`Bạn có đồng ý xoá sản phẩm có ID là ${productId} không?`);
    // Hiển thị modal xác nhận xoá (nếu cần)
    $('#confirmDeleteModal').modal('show');
    // Lưu productId vào thuộc tính data-product-id của modal để sử dụng sau này
    $('#confirmDeleteModal').data('product-id', productId);
    console.log(productId);
}

//xoá sản phẩm

function deleteProduct() {
    //id sản phẩm cần xoá
    var productId = $('#confirmDeleteModal').data('product-id');

    fetch(`home/delete-product?id=${productId}`, {
        method: "DELETE",
    })
        .then(res => res.json())
        .then(data => {
            if(data.statusCode === 200)
                location.reload();
        })
        .catch(err => console.log(err))
    // Đóng modal
    $('#confirmDeleteModal').modal('hide');
}