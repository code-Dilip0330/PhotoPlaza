document.getElementById("toggle-btn").addEventListener("click", function () {
    const sidebar = document.querySelector(".sidebar");
    const mainWrapper = document.querySelector(".main-wrapper");

    sidebar.classList.toggle("collapsed");
    mainWrapper.classList.toggle("collapsed");
  });

