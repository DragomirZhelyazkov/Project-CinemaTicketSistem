package data.consts;

public enum AdminOption {
        ADD_MOVIE(1),
        LOGOUT(2);

        private final int value;
        AdminOption(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static AdminOption convertToAdminOption(int option) {
            for (AdminOption adminOption : values()) {
                if(option == adminOption.value) return adminOption;
            }
            return null;
        }

}
