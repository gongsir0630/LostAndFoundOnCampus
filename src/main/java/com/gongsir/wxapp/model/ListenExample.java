package com.gongsir.wxapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ListenExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andLisTypeIsNull() {
            addCriterion("lis_type is null");
            return (Criteria) this;
        }

        public Criteria andLisTypeIsNotNull() {
            addCriterion("lis_type is not null");
            return (Criteria) this;
        }

        public Criteria andLisTypeEqualTo(String value) {
            addCriterion("lis_type =", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeNotEqualTo(String value) {
            addCriterion("lis_type <>", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeGreaterThan(String value) {
            addCriterion("lis_type >", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeGreaterThanOrEqualTo(String value) {
            addCriterion("lis_type >=", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeLessThan(String value) {
            addCriterion("lis_type <", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeLessThanOrEqualTo(String value) {
            addCriterion("lis_type <=", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeLike(String value) {
            addCriterion("lis_type like", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeNotLike(String value) {
            addCriterion("lis_type not like", value, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeIn(List<String> values) {
            addCriterion("lis_type in", values, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeNotIn(List<String> values) {
            addCriterion("lis_type not in", values, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeBetween(String value1, String value2) {
            addCriterion("lis_type between", value1, value2, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisTypeNotBetween(String value1, String value2) {
            addCriterion("lis_type not between", value1, value2, "lisType");
            return (Criteria) this;
        }

        public Criteria andLisNumIsNull() {
            addCriterion("lis_num is null");
            return (Criteria) this;
        }

        public Criteria andLisNumIsNotNull() {
            addCriterion("lis_num is not null");
            return (Criteria) this;
        }

        public Criteria andLisNumEqualTo(String value) {
            addCriterion("lis_num =", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumNotEqualTo(String value) {
            addCriterion("lis_num <>", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumGreaterThan(String value) {
            addCriterion("lis_num >", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumGreaterThanOrEqualTo(String value) {
            addCriterion("lis_num >=", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumLessThan(String value) {
            addCriterion("lis_num <", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumLessThanOrEqualTo(String value) {
            addCriterion("lis_num <=", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumLike(String value) {
            addCriterion("lis_num like", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumNotLike(String value) {
            addCriterion("lis_num not like", value, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumIn(List<String> values) {
            addCriterion("lis_num in", values, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumNotIn(List<String> values) {
            addCriterion("lis_num not in", values, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumBetween(String value1, String value2) {
            addCriterion("lis_num between", value1, value2, "lisNum");
            return (Criteria) this;
        }

        public Criteria andLisNumNotBetween(String value1, String value2) {
            addCriterion("lis_num not between", value1, value2, "lisNum");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andLisStatusIsNull() {
            addCriterion("lis_status is null");
            return (Criteria) this;
        }

        public Criteria andLisStatusIsNotNull() {
            addCriterion("lis_status is not null");
            return (Criteria) this;
        }

        public Criteria andLisStatusEqualTo(String value) {
            addCriterion("lis_status =", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusNotEqualTo(String value) {
            addCriterion("lis_status <>", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusGreaterThan(String value) {
            addCriterion("lis_status >", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusGreaterThanOrEqualTo(String value) {
            addCriterion("lis_status >=", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusLessThan(String value) {
            addCriterion("lis_status <", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusLessThanOrEqualTo(String value) {
            addCriterion("lis_status <=", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusLike(String value) {
            addCriterion("lis_status like", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusNotLike(String value) {
            addCriterion("lis_status not like", value, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusIn(List<String> values) {
            addCriterion("lis_status in", values, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusNotIn(List<String> values) {
            addCriterion("lis_status not in", values, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusBetween(String value1, String value2) {
            addCriterion("lis_status between", value1, value2, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisStatusNotBetween(String value1, String value2) {
            addCriterion("lis_status not between", value1, value2, "lisStatus");
            return (Criteria) this;
        }

        public Criteria andLisTimeIsNull() {
            addCriterion("lis_time is null");
            return (Criteria) this;
        }

        public Criteria andLisTimeIsNotNull() {
            addCriterion("lis_time is not null");
            return (Criteria) this;
        }

        public Criteria andLisTimeEqualTo(Date value) {
            addCriterion("lis_time =", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeNotEqualTo(Date value) {
            addCriterion("lis_time <>", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeGreaterThan(Date value) {
            addCriterion("lis_time >", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lis_time >=", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeLessThan(Date value) {
            addCriterion("lis_time <", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeLessThanOrEqualTo(Date value) {
            addCriterion("lis_time <=", value, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeIn(List<Date> values) {
            addCriterion("lis_time in", values, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeNotIn(List<Date> values) {
            addCriterion("lis_time not in", values, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeBetween(Date value1, Date value2) {
            addCriterion("lis_time between", value1, value2, "lisTime");
            return (Criteria) this;
        }

        public Criteria andLisTimeNotBetween(Date value1, Date value2) {
            addCriterion("lis_time not between", value1, value2, "lisTime");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(String value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(String value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(String value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(String value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(String value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLike(String value) {
            addCriterion("form_id like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotLike(String value) {
            addCriterion("form_id not like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<String> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<String> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(String value1, String value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(String value1, String value2) {
            addCriterion("form_id not between", value1, value2, "formId");
            return (Criteria) this;
        }
    }

    /**
     */
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