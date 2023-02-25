package ljwf.upload;

public class InputField {
        private String inputName; //输入区名
        private String inputContent; //输入内容
        private String[] inputmultiContent; //输入多项内容

        public void setInputName(String inputName) {
                this.inputName = inputName;
        }
        public void setInputContent(String inputContent) {
                this.inputContent = inputContent;
        }
        public void setInputmultiContent(String[] inputmultiContent) {
                this.inputmultiContent = inputmultiContent;
        }

        public String getInputName() {
                return inputName;
        }
        public String getInputContent() {
                return inputContent;
        }
        public String[] getInputmultiContent() {
                return inputmultiContent;
        }
}
