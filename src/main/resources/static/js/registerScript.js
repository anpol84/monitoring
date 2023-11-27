const department = document.getElementById("departmentDiv");
const specialization = document.getElementById("specializationDiv");
const roleInputs = document.querySelectorAll('input[name="role"]');
for (let i = 0; i < roleInputs.length; i++) {
    roleInputs[i].addEventListener('change', function() {
        const roleInput = document.querySelector('input[name="role"]:checked');
        if (roleInput) {
            const role = roleInput.value;
            if (role === "ROLE_STUDENT") {
                department.style.display = "none";
                specialization.style.display = "block";
            } else {
                department.style.display = "block";
                specialization.style.display = "none";
            }
        }
    });
}
// Скрываем список изначально
department.style.display = "none";
specialization.style.display = "none";

