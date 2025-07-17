console.log("script.js loaded");

document.addEventListener("DOMContentLoaded", () => {
    const links = document.querySelectorAll("a.button");
    links.forEach(link => {
        link.addEventListener("click", () => {
            console.log("Button clicked:", link.href);
        });
    });
});

