const notifications = [
            { type: 'order', title: 'New Order Received', time: '5 min ago', unread: true },
            { type: 'payment', title: 'Payment Confirmed', time: '2 hours ago', unread: true },
            { type: 'review', title: 'New Review Posted', time: '1 day ago', unread: false },
            { type: 'system', title: 'System Update', time: '3 days ago', unread: false }
        ];

        // Icon mapping
        const icons = {
            order: 'fas fa-shopping-cart',
            payment: 'fas fa-credit-card',
            review: 'fas fa-star',
            system: 'fas fa-exclamation-circle'
        };

        // Render functions
        function renderDropdown() {
            const dropdown = document.getElementById('dropdown');
            dropdown.innerHTML = notifications.map(notification => `
                <div class="dropdown-item ${notification.unread ? 'unread' : ''}" onclick="handleNotificationClick('${notification.type}')">
                    <i class="${icons[notification.type]} notification-icon"></i>
                    <div>
                        <div>${notification.title}</div>
                        <div class="timestamp">${notification.time}</div>
                    </div>
                </div>
            `).join('');
        }

        function renderNotifications() {
            const grid = document.getElementById('notificationGrid');
            grid.innerHTML = notifications.map(notification => `
                <div class="notification-card ${notification.unread ? 'unread' : ''}" onclick="handleNotificationClick('${notification.type}')">
                    <i class="${icons[notification.type]} notification-icon"></i>
                    <div>
                        <div>${notification.title}</div>
                        <div class="timestamp">${notification.time}</div>
                    </div>
                </div>
            `).join('');
        }

        // Interaction functions
        function toggleDropdown() {
            const dropdown = document.getElementById('dropdown');
            dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
        }

        function markAllAsRead() {
            notifications.forEach(notification => notification.unread = false);
            renderDropdown();
            renderNotifications();
            updateBadge();
        }

        function clearAll() {
            notifications.length = 0;
            renderDropdown();
            renderNotifications();
            updateBadge();
        }

        function handleNotificationClick(type) {
            // Add your navigation logic here
            console.log(`Redirecting to ${type} details...`);
        }

        function updateBadge() {
            const unreadCount = notifications.filter(n => n.unread).length;
            document.querySelector('.badge').textContent = unreadCount;
        }

        // Close dropdown when clicking outside
        document.addEventListener('click', (e) => {
            if (!e.target.closest('.notification-bell')) {
                document.getElementById('dropdown').style.display = 'none';
            }
        });

        // Initial render
        renderDropdown();
        renderNotifications();
        updateBadge();