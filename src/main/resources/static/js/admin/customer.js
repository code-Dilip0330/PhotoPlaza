function blockUser(userId) {
      if (confirm("Are you sure you want to block this user?")) {
        window.location.href = `/customers/block/${userId}`;
      }
    }

    function unblockUser(userId) {
      if (confirm("Are you sure you want to unblock this user?")) {
        window.location.href = `/customers/unblock/${userId}`;
      }
    }

    function archiveUser(userId) {
      if (confirm("Are you sure you want to archive this user?")) {
        window.location.href = `/customers/archive/${userId}`;
      }
    }

    function viewDetails(userId) {
      window.location.href = `/customers/${userId}/details`;
    }