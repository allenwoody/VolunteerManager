package com.allen.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andActivityDateIsNull() {
            addCriterion("activity_date is null");
            return (Criteria) this;
        }

        public Criteria andActivityDateIsNotNull() {
            addCriterion("activity_date is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDateEqualTo(Date value) {
            addCriterionForJDBCDate("activity_date =", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("activity_date <>", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateGreaterThan(Date value) {
            addCriterionForJDBCDate("activity_date >", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("activity_date >=", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateLessThan(Date value) {
            addCriterionForJDBCDate("activity_date <", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("activity_date <=", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateIn(List<Date> values) {
            addCriterionForJDBCDate("activity_date in", values, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("activity_date not in", values, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("activity_date between", value1, value2, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("activity_date not between", value1, value2, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityProfileIsNull() {
            addCriterion("activity_profile is null");
            return (Criteria) this;
        }

        public Criteria andActivityProfileIsNotNull() {
            addCriterion("activity_profile is not null");
            return (Criteria) this;
        }

        public Criteria andActivityProfileEqualTo(String value) {
            addCriterion("activity_profile =", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileNotEqualTo(String value) {
            addCriterion("activity_profile <>", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileGreaterThan(String value) {
            addCriterion("activity_profile >", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileGreaterThanOrEqualTo(String value) {
            addCriterion("activity_profile >=", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileLessThan(String value) {
            addCriterion("activity_profile <", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileLessThanOrEqualTo(String value) {
            addCriterion("activity_profile <=", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileLike(String value) {
            addCriterion("activity_profile like", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileNotLike(String value) {
            addCriterion("activity_profile not like", value, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileIn(List<String> values) {
            addCriterion("activity_profile in", values, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileNotIn(List<String> values) {
            addCriterion("activity_profile not in", values, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileBetween(String value1, String value2) {
            addCriterion("activity_profile between", value1, value2, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityProfileNotBetween(String value1, String value2) {
            addCriterion("activity_profile not between", value1, value2, "activityProfile");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNull() {
            addCriterion("activity_address is null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNotNull() {
            addCriterion("activity_address is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressEqualTo(String value) {
            addCriterion("activity_address =", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotEqualTo(String value) {
            addCriterion("activity_address <>", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThan(String value) {
            addCriterion("activity_address >", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThanOrEqualTo(String value) {
            addCriterion("activity_address >=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThan(String value) {
            addCriterion("activity_address <", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThanOrEqualTo(String value) {
            addCriterion("activity_address <=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLike(String value) {
            addCriterion("activity_address like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotLike(String value) {
            addCriterion("activity_address not like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIn(List<String> values) {
            addCriterion("activity_address in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotIn(List<String> values) {
            addCriterion("activity_address not in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressBetween(String value1, String value2) {
            addCriterion("activity_address between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotBetween(String value1, String value2) {
            addCriterion("activity_address not between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(String value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(String value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(String value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(String value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(String value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(String value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLike(String value) {
            addCriterion("is_valid like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotLike(String value) {
            addCriterion("is_valid not like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<String> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<String> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(String value1, String value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(String value1, String value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
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