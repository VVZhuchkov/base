var isOpen = false;
function openSlideMenu () {
    if (isOpen) {
        document.getElementById('menu').style.width = '0';
        document.getElementById('content').style.marginLeft = '0';
        isOpen = false;
    } else {
        document.getElementById('menu').style.width = '180px';
        document.getElementById('content').style.marginLeft = '180px';
        isOpen=true;
    }
}