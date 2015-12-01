package cn.edu.hit.ir.ltp;

public enum Task {
    WS("ws"),
    POS("pos"),
    NER("ner"),
    DP("dp"),
    SDP("sdp"),
    SRL("srl"),
    ALL("all");

    private final String value;

    Task(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
