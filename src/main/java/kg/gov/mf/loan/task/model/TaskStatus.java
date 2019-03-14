package kg.gov.mf.loan.task.model;

public enum TaskStatus {
    OPEN {
        @Override
        public String text() {
            return "ОТКРЫТ";
        }
    },
    CLOSED {
        @Override
        public String text() {
            return "ЗАКРЫТ";
        }
    },
    ON_HOLD {
        @Override
        public String text() {
            return "Пауза";
        }
    },
    CANCELED {
        @Override
        public String text() {
            return "Отменен";
        }
    },
    ERROR {
        @Override
        public String text() {
            return "Ошибка";
        }
    };

    public abstract String text();
}
