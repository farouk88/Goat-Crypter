// Smooth appear on scroll
document.addEventListener('DOMContentLoaded', function() {
    var appearElements = document.querySelectorAll('.smooth-appear');
    var appearOnScroll = new IntersectionObserver(function(entries, appearOnScroll) {
        entries.forEach(function(entry) {
            if (!entry.isIntersecting) {
                return;
            } else {
                entry.target.classList.add('active');
                appearOnScroll.unobserve(entry.target);
            }
        });
    }, { threshold: 0.1 });

    appearElements.forEach(function(elem) {
        appearOnScroll.observe(elem);
    });
});
