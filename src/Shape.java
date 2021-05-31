public enum Shape {
    Spades {
        @Override
        public String toString() {
            return "♠";
        }
    },

    Diamonds {
        @Override
        public String toString() {
            return "♦";
        }
    },

    Clubs {
        @Override
        public String toString() {
            return "♣";
        }
    },

    Hearts {
        @Override
        public String toString() {
            return "♥";
        }
    }
}
