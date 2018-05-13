//package test.pattern;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by full on 2018/5/12.
// */
//public class Order {
//    private String code;
//    private List<String> offers;
//    private Map<String, Object> features;
//
//    public static Order.Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder {
//        private OrderState orderState = new OrderState();
//        private static final BeanCopier orderCopier = BeanCopier.create(OrderState.class, Order1.class, false);
//
//        private class OrderState {
//            private String code;
//            private Map<String, Object> features;
//            private List<String> offers;
//
//            public String getCode() {
//                return code;
//            }
//
//            public void setCode(String code) {
//                this.code = code;
//            }
//
//            public Map<String, Object> getFeatures() {
//                return features;
//            }
//
//            public void setFeatures(Map<String, Object> features) {
//                this.features = features;
//            }
//
//            public List<String> getOffers() {
//                return offers;
//            }
//
//            public void setOffers(List<String> offers) {
//                this.offers = offers;
//            }
//        }
//
//        public Builder code(String code) {
//            orderState.code = code;
//            return this;
//        }
//
//        public Builder features(Map<String, Object> features) {
//            orderState.features = features;
//            return this;
//        }
//
//        public <T> Builder feature(String key, T obj) {
//            if (orderState.features == null) {
////                orderState.features = new HashMap<>();
//            }
//            orderState.features.put(key, obj);
//            return this;
//        }
//
//        public Builder offers(List<String> offers) {
//            orderState.offers = offers;
//            return this;
//        }
//
//        public Builder offer(String offer) {
//            if (orderState.offers == null) {
//                orderState.offers = new ArrayList<>();
//            }
//            orderState.offers.add(offer);
//            return this;
//        }
//
//        public Order build() {
//            Order order = new Order();
//            orderCopier.copy(orderState, order1, null);
//            orderState = null;
//            return order;
//        }
//    }
//
//
//
//
//    // get set
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public List<String> getOffers() {
//        return offers;
//    }
//
//    public void setOffers(List<String> offers) {
//        this.offers = offers;
//    }
//
//    public Map<String, Object> getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(Map<String, Object> features) {
//        this.features = features;
//    }
//}