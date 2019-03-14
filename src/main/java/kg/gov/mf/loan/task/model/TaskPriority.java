package kg.gov.mf.loan.task.model;

public enum TaskPriority {
    HIGH {
        @Override
        public String text() {
            return "Высокий";
        }
            },
    MEDIUM {
        @Override
        public String text() {
            return "Средний";
        }
    },
    LOW {
        @Override
        public String text() {
            return "Низкий";
        }
    };

    public abstract String text();
}
