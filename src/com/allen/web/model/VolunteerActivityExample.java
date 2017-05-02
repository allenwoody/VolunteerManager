package com.allen.web.model;

import java.util.ArrayList;
import java.util.List;

public class VolunteerActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VolunteerActivityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andVaIdIsNull() {
            addCriterion("va_id is null");
            return (Criteria) this;
        }

        public Criteria andVaIdIsNotNull() {
            addCriterion("va_id is not null");
            return (Criteria) this;
        }

        public Criteria andVaIdEqualTo(String value) {
            addCriterion("va_id =", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdNotEqualTo(String value) {
            addCriterion("va_id <>", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdGreaterThan(String value) {
            addCriterion("va_id >", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdGreaterThanOrEqualTo(String value) {
            addCriterion("va_id >=", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdLessThan(String value) {
            addCriterion("va_id <", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdLessThanOrEqualTo(String value) {
            addCriterion("va_id <=", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdLike(String value) {
            addCriterion("va_id like", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdNotLike(String value) {
            addCriterion("va_id not like", value, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdIn(List<String> values) {
            addCriterion("va_id in", values, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdNotIn(List<String> values) {
            addCriterion("va_id not in", values, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdBetween(String value1, String value2) {
            addCriterion("va_id between", value1, value2, "vaId");
            return (Criteria) this;
        }

        public Criteria andVaIdNotBetween(String value1, String value2) {
            addCriterion("va_id not between", value1, value2, "vaId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdIsNull() {
            addCriterion("volunteer_id is null");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdIsNotNull() {
            addCriterion("volunteer_id is not null");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdEqualTo(String value) {
            addCriterion("volunteer_id =", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdNotEqualTo(String value) {
            addCriterion("volunteer_id <>", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdGreaterThan(String value) {
            addCriterion("volunteer_id >", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdGreaterThanOrEqualTo(String value) {
            addCriterion("volunteer_id >=", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdLessThan(String value) {
            addCriterion("volunteer_id <", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdLessThanOrEqualTo(String value) {
            addCriterion("volunteer_id <=", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdLike(String value) {
            addCriterion("volunteer_id like", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdNotLike(String value) {
            addCriterion("volunteer_id not like", value, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdIn(List<String> values) {
            addCriterion("volunteer_id in", values, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdNotIn(List<String> values) {
            addCriterion("volunteer_id not in", values, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdBetween(String value1, String value2) {
            addCriterion("volunteer_id between", value1, value2, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerIdNotBetween(String value1, String value2) {
            addCriterion("volunteer_id not between", value1, value2, "volunteerId");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameIsNull() {
            addCriterion("volunteer_name is null");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameIsNotNull() {
            addCriterion("volunteer_name is not null");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameEqualTo(String value) {
            addCriterion("volunteer_name =", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameNotEqualTo(String value) {
            addCriterion("volunteer_name <>", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameGreaterThan(String value) {
            addCriterion("volunteer_name >", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameGreaterThanOrEqualTo(String value) {
            addCriterion("volunteer_name >=", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameLessThan(String value) {
            addCriterion("volunteer_name <", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameLessThanOrEqualTo(String value) {
            addCriterion("volunteer_name <=", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameLike(String value) {
            addCriterion("volunteer_name like", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameNotLike(String value) {
            addCriterion("volunteer_name not like", value, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameIn(List<String> values) {
            addCriterion("volunteer_name in", values, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameNotIn(List<String> values) {
            addCriterion("volunteer_name not in", values, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameBetween(String value1, String value2) {
            addCriterion("volunteer_name between", value1, value2, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andVolunteerNameNotBetween(String value1, String value2) {
            addCriterion("volunteer_name not between", value1, value2, "volunteerName");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(String value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(String value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(String value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(String value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(String value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(String value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLike(String value) {
            addCriterion("activity_id like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotLike(String value) {
            addCriterion("activity_id not like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<String> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<String> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(String value1, String value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(String value1, String value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}