package rule;

public record Rule(String id, String rule) {
    public Rule {
        if (id.isEmpty() || rule.isEmpty()) {
            throw new NullPointerException("Must have a value");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule1 = (Rule) o;

        if (!id.equals(rule1.id)) return false;
        return rule.equals(rule1.rule);
    }

}
