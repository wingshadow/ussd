package ljwf.upload;

public class InputField {
        private String inputName; //��������
        private String inputContent; //��������
        private String[] inputmultiContent; //�����������

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
