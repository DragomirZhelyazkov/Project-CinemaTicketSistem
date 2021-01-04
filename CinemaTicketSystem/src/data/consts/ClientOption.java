package data.consts;

public enum ClientOption {
        BUY_TICKET(1),
        LOGOUT(2);

        private final int value;

        ClientOption(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static ClientOption convertToClientOption(int option) {
            for (ClientOption clientOption : values()) {
                if(option == clientOption.value) return clientOption;
            }
            return null;
        }

}
