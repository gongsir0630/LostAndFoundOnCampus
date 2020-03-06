package com.gongsir.wxapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ImageExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andImgTitleIsNull() {
            addCriterion("img_title is null");
            return (Criteria) this;
        }

        public Criteria andImgTitleIsNotNull() {
            addCriterion("img_title is not null");
            return (Criteria) this;
        }

        public Criteria andImgTitleEqualTo(String value) {
            addCriterion("img_title =", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleNotEqualTo(String value) {
            addCriterion("img_title <>", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleGreaterThan(String value) {
            addCriterion("img_title >", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleGreaterThanOrEqualTo(String value) {
            addCriterion("img_title >=", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleLessThan(String value) {
            addCriterion("img_title <", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleLessThanOrEqualTo(String value) {
            addCriterion("img_title <=", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleLike(String value) {
            addCriterion("img_title like", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleNotLike(String value) {
            addCriterion("img_title not like", value, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleIn(List<String> values) {
            addCriterion("img_title in", values, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleNotIn(List<String> values) {
            addCriterion("img_title not in", values, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleBetween(String value1, String value2) {
            addCriterion("img_title between", value1, value2, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgTitleNotBetween(String value1, String value2) {
            addCriterion("img_title not between", value1, value2, "imgTitle");
            return (Criteria) this;
        }

        public Criteria andImgLinkIsNull() {
            addCriterion("img_link is null");
            return (Criteria) this;
        }

        public Criteria andImgLinkIsNotNull() {
            addCriterion("img_link is not null");
            return (Criteria) this;
        }

        public Criteria andImgLinkEqualTo(String value) {
            addCriterion("img_link =", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotEqualTo(String value) {
            addCriterion("img_link <>", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkGreaterThan(String value) {
            addCriterion("img_link >", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkGreaterThanOrEqualTo(String value) {
            addCriterion("img_link >=", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLessThan(String value) {
            addCriterion("img_link <", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLessThanOrEqualTo(String value) {
            addCriterion("img_link <=", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLike(String value) {
            addCriterion("img_link like", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotLike(String value) {
            addCriterion("img_link not like", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkIn(List<String> values) {
            addCriterion("img_link in", values, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotIn(List<String> values) {
            addCriterion("img_link not in", values, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkBetween(String value1, String value2) {
            addCriterion("img_link between", value1, value2, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotBetween(String value1, String value2) {
            addCriterion("img_link not between", value1, value2, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgStatusIsNull() {
            addCriterion("img_status is null");
            return (Criteria) this;
        }

        public Criteria andImgStatusIsNotNull() {
            addCriterion("img_status is not null");
            return (Criteria) this;
        }

        public Criteria andImgStatusEqualTo(String value) {
            addCriterion("img_status =", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusNotEqualTo(String value) {
            addCriterion("img_status <>", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusGreaterThan(String value) {
            addCriterion("img_status >", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusGreaterThanOrEqualTo(String value) {
            addCriterion("img_status >=", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusLessThan(String value) {
            addCriterion("img_status <", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusLessThanOrEqualTo(String value) {
            addCriterion("img_status <=", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusLike(String value) {
            addCriterion("img_status like", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusNotLike(String value) {
            addCriterion("img_status not like", value, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusIn(List<String> values) {
            addCriterion("img_status in", values, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusNotIn(List<String> values) {
            addCriterion("img_status not in", values, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusBetween(String value1, String value2) {
            addCriterion("img_status between", value1, value2, "imgStatus");
            return (Criteria) this;
        }

        public Criteria andImgStatusNotBetween(String value1, String value2) {
            addCriterion("img_status not between", value1, value2, "imgStatus");
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