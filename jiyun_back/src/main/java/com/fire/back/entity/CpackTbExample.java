package com.fire.back.entity;

import java.util.ArrayList;
import java.util.List;

public class CpackTbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CpackTbExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPackIdIsNull() {
            addCriterion("pack_id is null");
            return (Criteria) this;
        }

        public Criteria andPackIdIsNotNull() {
            addCriterion("pack_id is not null");
            return (Criteria) this;
        }

        public Criteria andPackIdEqualTo(Long value) {
            addCriterion("pack_id =", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdNotEqualTo(Long value) {
            addCriterion("pack_id <>", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdGreaterThan(Long value) {
            addCriterion("pack_id >", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pack_id >=", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdLessThan(Long value) {
            addCriterion("pack_id <", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdLessThanOrEqualTo(Long value) {
            addCriterion("pack_id <=", value, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdIn(List<Long> values) {
            addCriterion("pack_id in", values, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdNotIn(List<Long> values) {
            addCriterion("pack_id not in", values, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdBetween(Long value1, Long value2) {
            addCriterion("pack_id between", value1, value2, "packId");
            return (Criteria) this;
        }

        public Criteria andPackIdNotBetween(Long value1, Long value2) {
            addCriterion("pack_id not between", value1, value2, "packId");
            return (Criteria) this;
        }

        public Criteria andDeliverIsNull() {
            addCriterion("deliver is null");
            return (Criteria) this;
        }

        public Criteria andDeliverIsNotNull() {
            addCriterion("deliver is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverEqualTo(String value) {
            addCriterion("deliver =", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotEqualTo(String value) {
            addCriterion("deliver <>", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverGreaterThan(String value) {
            addCriterion("deliver >", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverGreaterThanOrEqualTo(String value) {
            addCriterion("deliver >=", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverLessThan(String value) {
            addCriterion("deliver <", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverLessThanOrEqualTo(String value) {
            addCriterion("deliver <=", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverLike(String value) {
            addCriterion("deliver like", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotLike(String value) {
            addCriterion("deliver not like", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverIn(List<String> values) {
            addCriterion("deliver in", values, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotIn(List<String> values) {
            addCriterion("deliver not in", values, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverBetween(String value1, String value2) {
            addCriterion("deliver between", value1, value2, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotBetween(String value1, String value2) {
            addCriterion("deliver not between", value1, value2, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberIsNull() {
            addCriterion("deliver_number is null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberIsNotNull() {
            addCriterion("deliver_number is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberEqualTo(String value) {
            addCriterion("deliver_number =", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberNotEqualTo(String value) {
            addCriterion("deliver_number <>", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberGreaterThan(String value) {
            addCriterion("deliver_number >", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberGreaterThanOrEqualTo(String value) {
            addCriterion("deliver_number >=", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberLessThan(String value) {
            addCriterion("deliver_number <", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberLessThanOrEqualTo(String value) {
            addCriterion("deliver_number <=", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberLike(String value) {
            addCriterion("deliver_number like", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberNotLike(String value) {
            addCriterion("deliver_number not like", value, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberIn(List<String> values) {
            addCriterion("deliver_number in", values, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberNotIn(List<String> values) {
            addCriterion("deliver_number not in", values, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberBetween(String value1, String value2) {
            addCriterion("deliver_number between", value1, value2, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andDeliverNumberNotBetween(String value1, String value2) {
            addCriterion("deliver_number not between", value1, value2, "deliverNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNull() {
            addCriterion("goods is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNotNull() {
            addCriterion("goods is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEqualTo(String value) {
            addCriterion("goods =", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotEqualTo(String value) {
            addCriterion("goods <>", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThan(String value) {
            addCriterion("goods >", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("goods >=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThan(String value) {
            addCriterion("goods <", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThanOrEqualTo(String value) {
            addCriterion("goods <=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLike(String value) {
            addCriterion("goods like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotLike(String value) {
            addCriterion("goods not like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsIn(List<String> values) {
            addCriterion("goods in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotIn(List<String> values) {
            addCriterion("goods not in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsBetween(String value1, String value2) {
            addCriterion("goods between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotBetween(String value1, String value2) {
            addCriterion("goods not between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoosNumberIsNull() {
            addCriterion("goos_number is null");
            return (Criteria) this;
        }

        public Criteria andGoosNumberIsNotNull() {
            addCriterion("goos_number is not null");
            return (Criteria) this;
        }

        public Criteria andGoosNumberEqualTo(String value) {
            addCriterion("goos_number =", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberNotEqualTo(String value) {
            addCriterion("goos_number <>", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberGreaterThan(String value) {
            addCriterion("goos_number >", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberGreaterThanOrEqualTo(String value) {
            addCriterion("goos_number >=", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberLessThan(String value) {
            addCriterion("goos_number <", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberLessThanOrEqualTo(String value) {
            addCriterion("goos_number <=", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberLike(String value) {
            addCriterion("goos_number like", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberNotLike(String value) {
            addCriterion("goos_number not like", value, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberIn(List<String> values) {
            addCriterion("goos_number in", values, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberNotIn(List<String> values) {
            addCriterion("goos_number not in", values, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberBetween(String value1, String value2) {
            addCriterion("goos_number between", value1, value2, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andGoosNumberNotBetween(String value1, String value2) {
            addCriterion("goos_number not between", value1, value2, "goosNumber");
            return (Criteria) this;
        }

        public Criteria andWorthIsNull() {
            addCriterion("worth is null");
            return (Criteria) this;
        }

        public Criteria andWorthIsNotNull() {
            addCriterion("worth is not null");
            return (Criteria) this;
        }

        public Criteria andWorthEqualTo(String value) {
            addCriterion("worth =", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotEqualTo(String value) {
            addCriterion("worth <>", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthGreaterThan(String value) {
            addCriterion("worth >", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthGreaterThanOrEqualTo(String value) {
            addCriterion("worth >=", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthLessThan(String value) {
            addCriterion("worth <", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthLessThanOrEqualTo(String value) {
            addCriterion("worth <=", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthLike(String value) {
            addCriterion("worth like", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotLike(String value) {
            addCriterion("worth not like", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthIn(List<String> values) {
            addCriterion("worth in", values, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotIn(List<String> values) {
            addCriterion("worth not in", values, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthBetween(String value1, String value2) {
            addCriterion("worth between", value1, value2, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotBetween(String value1, String value2) {
            addCriterion("worth not between", value1, value2, "worth");
            return (Criteria) this;
        }

        public Criteria andBuyUrlIsNull() {
            addCriterion("buy_url is null");
            return (Criteria) this;
        }

        public Criteria andBuyUrlIsNotNull() {
            addCriterion("buy_url is not null");
            return (Criteria) this;
        }

        public Criteria andBuyUrlEqualTo(String value) {
            addCriterion("buy_url =", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlNotEqualTo(String value) {
            addCriterion("buy_url <>", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlGreaterThan(String value) {
            addCriterion("buy_url >", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("buy_url >=", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlLessThan(String value) {
            addCriterion("buy_url <", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlLessThanOrEqualTo(String value) {
            addCriterion("buy_url <=", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlLike(String value) {
            addCriterion("buy_url like", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlNotLike(String value) {
            addCriterion("buy_url not like", value, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlIn(List<String> values) {
            addCriterion("buy_url in", values, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlNotIn(List<String> values) {
            addCriterion("buy_url not in", values, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlBetween(String value1, String value2) {
            addCriterion("buy_url between", value1, value2, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andBuyUrlNotBetween(String value1, String value2) {
            addCriterion("buy_url not between", value1, value2, "buyUrl");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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