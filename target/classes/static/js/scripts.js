(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    let forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

function showNumberDetails() {
    const form = document.getElementById("phone-number-search-form");
    if (form.checkValidity())
    {
        let phoneNumber = document.getElementById("phoneNumber").value;
        window.open("/user/" + phoneNumber);
    }
}