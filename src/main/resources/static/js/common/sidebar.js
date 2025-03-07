const toggleButton = document.getElementById('toggle-btn')
const sidebar = document.getElementById('sidebar')

 //try

 const sidebars = document.querySelector(".sidebar");
  const mainWrapper = document.querySelector(".main-wrapper");

 //try

function toggleSidebar(){
  //try
         sidebars.classList.toggle("collapsed");
         mainWrapper.classList.toggle("collapsed");

  //  try

console.log("it also working")
  sidebar.classList.toggle('close')
  toggleButton.classList.toggle('rotate')

  closeAllSubMenus()

}

function toggleSubMenu(button){

  if(!button.nextElementSibling.classList.contains('show')){
    closeAllSubMenus()
  }

  button.nextElementSibling.classList.toggle('show')
  button.classList.toggle('rotate')

  if(sidebar.classList.contains('close')){
    sidebar.classList.toggle('close')
    toggleButton.classList.toggle('rotate')
  }
}

function closeAllSubMenus(){
  Array.from(sidebar.getElementsByClassName('show')).forEach(ul => {
    ul.classList.remove('show')
    ul.previousElementSibling.classList.remove('rotate')
  })
}

