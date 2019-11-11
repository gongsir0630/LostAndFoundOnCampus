package com.gongsir.wxapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GoodExample() {
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

        public Criteria andGoodTitleIsNull() {
            addCriterion("good_title is null");
            return (Criteria) this;
        }

        public Criteria andGoodTitleIsNotNull() {
            addCriterion("good_title is not null");
            return (Criteria) this;
        }

        public Criteria andGoodTitleEqualTo(String value) {
            addCriterion("good_title =", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleNotEqualTo(String value) {
            addCriterion("good_title <>", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleGreaterThan(String value) {
            addCriterion("good_title >", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleGreaterThanOrEqualTo(String value) {
            addCriterion("good_title >=", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleLessThan(String value) {
            addCriterion("good_title <", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleLessThanOrEqualTo(String value) {
            addCriterion("good_title <=", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleLike(String value) {
            addCriterion("good_title like", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleNotLike(String value) {
            addCriterion("good_title not like", value, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleIn(List<String> values) {
            addCriterion("good_title in", values, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleNotIn(List<String> values) {
            addCriterion("good_title not in", values, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleBetween(String value1, String value2) {
            addCriterion("good_title between", value1, value2, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTitleNotBetween(String value1, String value2) {
            addCriterion("good_title not between", value1, value2, "goodTitle");
            return (Criteria) this;
        }

        public Criteria andGoodTextsIsNull() {
            addCriterion("good_texts is null");
            return (Criteria) this;
        }

        public Criteria andGoodTextsIsNotNull() {
            addCriterion("good_texts is not null");
            return (Criteria) this;
        }

        public Criteria andGoodTextsEqualTo(String value) {
            addCriterion("good_texts =", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsNotEqualTo(String value) {
            addCriterion("good_texts <>", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsGreaterThan(String value) {
            addCriterion("good_texts >", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsGreaterThanOrEqualTo(String value) {
            addCriterion("good_texts >=", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsLessThan(String value) {
            addCriterion("good_texts <", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsLessThanOrEqualTo(String value) {
            addCriterion("good_texts <=", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsLike(String value) {
            addCriterion("good_texts like", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsNotLike(String value) {
            addCriterion("good_texts not like", value, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsIn(List<String> values) {
            addCriterion("good_texts in", values, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsNotIn(List<String> values) {
            addCriterion("good_texts not in", values, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsBetween(String value1, String value2) {
            addCriterion("good_texts between", value1, value2, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodTextsNotBetween(String value1, String value2) {
            addCriterion("good_texts not between", value1, value2, "goodTexts");
            return (Criteria) this;
        }

        public Criteria andGoodClassIsNull() {
            addCriterion("good_class is null");
            return (Criteria) this;
        }

        public Criteria andGoodClassIsNotNull() {
            addCriterion("good_class is not null");
            return (Criteria) this;
        }

        public Criteria andGoodClassEqualTo(String value) {
            addCriterion("good_class =", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassNotEqualTo(String value) {
            addCriterion("good_class <>", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassGreaterThan(String value) {
            addCriterion("good_class >", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassGreaterThanOrEqualTo(String value) {
            addCriterion("good_class >=", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassLessThan(String value) {
            addCriterion("good_class <", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassLessThanOrEqualTo(String value) {
            addCriterion("good_class <=", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassLike(String value) {
            addCriterion("good_class like", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassNotLike(String value) {
            addCriterion("good_class not like", value, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassIn(List<String> values) {
            addCriterion("good_class in", values, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassNotIn(List<String> values) {
            addCriterion("good_class not in", values, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassBetween(String value1, String value2) {
            addCriterion("good_class between", value1, value2, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodClassNotBetween(String value1, String value2) {
            addCriterion("good_class not between", value1, value2, "goodClass");
            return (Criteria) this;
        }

        public Criteria andGoodImageIsNull() {
            addCriterion("good_image is null");
            return (Criteria) this;
        }

        public Criteria andGoodImageIsNotNull() {
            addCriterion("good_image is not null");
            return (Criteria) this;
        }

        public Criteria andGoodImageEqualTo(String value) {
            addCriterion("good_image =", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageNotEqualTo(String value) {
            addCriterion("good_image <>", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageGreaterThan(String value) {
            addCriterion("good_image >", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageGreaterThanOrEqualTo(String value) {
            addCriterion("good_image >=", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageLessThan(String value) {
            addCriterion("good_image <", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageLessThanOrEqualTo(String value) {
            addCriterion("good_image <=", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageLike(String value) {
            addCriterion("good_image like", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageNotLike(String value) {
            addCriterion("good_image not like", value, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageIn(List<String> values) {
            addCriterion("good_image in", values, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageNotIn(List<String> values) {
            addCriterion("good_image not in", values, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageBetween(String value1, String value2) {
            addCriterion("good_image between", value1, value2, "goodImage");
            return (Criteria) this;
        }

        public Criteria andGoodImageNotBetween(String value1, String value2) {
            addCriterion("good_image not between", value1, value2, "goodImage");
            return (Criteria) this;
        }

        public Criteria andRelationIsNull() {
            addCriterion("relation is null");
            return (Criteria) this;
        }

        public Criteria andRelationIsNotNull() {
            addCriterion("relation is not null");
            return (Criteria) this;
        }

        public Criteria andRelationEqualTo(String value) {
            addCriterion("relation =", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotEqualTo(String value) {
            addCriterion("relation <>", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThan(String value) {
            addCriterion("relation >", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThanOrEqualTo(String value) {
            addCriterion("relation >=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThan(String value) {
            addCriterion("relation <", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThanOrEqualTo(String value) {
            addCriterion("relation <=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLike(String value) {
            addCriterion("relation like", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotLike(String value) {
            addCriterion("relation not like", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationIn(List<String> values) {
            addCriterion("relation in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotIn(List<String> values) {
            addCriterion("relation not in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationBetween(String value1, String value2) {
            addCriterion("relation between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotBetween(String value1, String value2) {
            addCriterion("relation not between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIsNull() {
            addCriterion("good_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIsNotNull() {
            addCriterion("good_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodTypeEqualTo(String value) {
            addCriterion("good_type =", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotEqualTo(String value) {
            addCriterion("good_type <>", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeGreaterThan(String value) {
            addCriterion("good_type >", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeGreaterThanOrEqualTo(String value) {
            addCriterion("good_type >=", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLessThan(String value) {
            addCriterion("good_type <", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLessThanOrEqualTo(String value) {
            addCriterion("good_type <=", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLike(String value) {
            addCriterion("good_type like", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotLike(String value) {
            addCriterion("good_type not like", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIn(List<String> values) {
            addCriterion("good_type in", values, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotIn(List<String> values) {
            addCriterion("good_type not in", values, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeBetween(String value1, String value2) {
            addCriterion("good_type between", value1, value2, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotBetween(String value1, String value2) {
            addCriterion("good_type not between", value1, value2, "goodType");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andGoodStatusIsNull() {
            addCriterion("good_status is null");
            return (Criteria) this;
        }

        public Criteria andGoodStatusIsNotNull() {
            addCriterion("good_status is not null");
            return (Criteria) this;
        }

        public Criteria andGoodStatusEqualTo(String value) {
            addCriterion("good_status =", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusNotEqualTo(String value) {
            addCriterion("good_status <>", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusGreaterThan(String value) {
            addCriterion("good_status >", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusGreaterThanOrEqualTo(String value) {
            addCriterion("good_status >=", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusLessThan(String value) {
            addCriterion("good_status <", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusLessThanOrEqualTo(String value) {
            addCriterion("good_status <=", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusLike(String value) {
            addCriterion("good_status like", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusNotLike(String value) {
            addCriterion("good_status not like", value, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusIn(List<String> values) {
            addCriterion("good_status in", values, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusNotIn(List<String> values) {
            addCriterion("good_status not in", values, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusBetween(String value1, String value2) {
            addCriterion("good_status between", value1, value2, "goodStatus");
            return (Criteria) this;
        }

        public Criteria andGoodStatusNotBetween(String value1, String value2) {
            addCriterion("good_status not between", value1, value2, "goodStatus");
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