    import java.awt.*;
    import java.io.*;
    import java.util.*;
    import javax.swing.*;



    public class POOaoTrivia {


        int pontuacao;
        String optionText;
        boolean isCorrect;
        String[] questionTypeArray = new String[5];
        int value = 0;
        
        void incValue(){value++;}

        void addType(String[] list, Question question, int value){
            list[value] = question.typeQuestion;
            for (String amongus : list){
                System.out.println(amongus);
            }
         }

        public void mostrarTop3() {
            // Implemente a lógica para mostrar o TOP 3
        }

        private void salvarResultadoJogo() {
            // Implemente a lógica para salvar o resultado do jogo em um arquivo de objetos
        }

        public void jogar() {

        }
        

        

        public static ArrayList<Question> getQuestionsFromFile() {
            ArrayList<Question> questionList = new ArrayList<Question>();

            try {
                FileReader frd = new FileReader(new File("PerguntasPoo.txt"));
                BufferedReader fR = new BufferedReader(frd);
                String line;
                String[] optionStrings; //array temporario onde ficam guardado o texto de cada opção, de cada pergunta
                String[] footPlayersInfo; //array temporario onde fica guardado nome e numero do jogador

                while ((line = fR.readLine()) != null) {
                    if (line.contains("//")) {

                        String[] parts = line.split("//");
                        String classType = parts[0];
                        optionStrings = parts[2].split(",");
                        switch (classType) {
                            case ("Arte"):
                                Art artQuestion = new Art();  //declarar o tipo de questao
                                artQuestion.typeQuestion = parts[0];
                                artQuestion.question = parts[1]; //Guardar o texto da pergunta
                                for (String item : optionStrings) { //adicionar cada opção no array de opçoes da pergunta
                                    Option tempOption = new Option(item);
                                    artQuestion.arrayOption.add(tempOption);
                                }
                                questionList.add(artQuestion); //adicionar ao array de perguntas final
                                break;

                            case ("Ciencia"):
                                Science sciQuestion = new Science();
                                sciQuestion.typeQuestion = parts[0];
                                sciQuestion.question = parts[1];
                                String[] arrayOptionHard = parts[3].split(",");
                                
                                for (int i = 0; i < optionStrings.length; i++) {
                                    Option tempOption = new Option(optionStrings[i]);
                                    sciQuestion.arrayOption.add(tempOption);
                                    tempOption.opString = arrayOptionHard[i];
                                    sciQuestion.arrayOptionHard.add(tempOption);
                                }

                                questionList.add(sciQuestion);
                                break;

                            case ("Ski"):
                                Ski skiQuestion = new Ski();                                
                                skiQuestion.typeQuestion = parts[0];
                                skiQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    Option tempOption = new Option(item);
                                    skiQuestion.arrayOption.add(tempOption);
                                }

                                questionList.add(skiQuestion);
                                break;

                            case ("Natacao"):
                                Swimming swimQuestion = new Swimming();
                                swimQuestion.typeQuestion = parts[0];
                                swimQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    Option tempOption = new Option(item);
                                    swimQuestion.arrayOption.add(tempOption);
                                }

                                questionList.add(swimQuestion);
                                break;

                            case ("Futebol"):
                                Football footQuestion = new Football();
                                footQuestion.typeQuestion = parts[0];
                                footQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    footPlayersInfo = item.split(";");
                                    FootballPlayer tempFootballPlayer = new FootballPlayer();
                                    tempFootballPlayer.playerName = footPlayersInfo[0];
                                    tempFootballPlayer.shirtNumber = footPlayersInfo[1];
                                    footQuestion.arrayPlayers.add(tempFootballPlayer);
                                }
                                questionList.add(footQuestion);
                                break;
                            default:
                                break;
                        }

                    }

                }

                frd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return questionList;
        }
        
        public static Question obterPerguntaAleatoria(ArrayList<Question> questionList, String[] questionTypeArray, int value) {
            if (questionList != null && !questionList.isEmpty()) {
                Random random = new Random();
                Question randomQuestion = null;

                // Loop until a question with options is found
                while (randomQuestion == null || randomQuestion.getOptions().isEmpty()) {
                    int indiceAleatorio = random.nextInt(questionList.size());
                    int flag = 0;

                    
                    if (questionTypeArray[0] != null){
                        for (int i = 0; i < questionTypeArray.length; i++ ){
                            if (questionList.get(indiceAleatorio).typeQuestion.equals(questionTypeArray[i])){
                                flag = 1;
                                break; 
                            } 
  
                        }
                    }
                    if (flag == 0){ 
                        switch (questionList.get(indiceAleatorio).typeQuestion){
                            case ("Arte"):
                                Art randomArt = new Art(questionList.get(indiceAleatorio).arrayOption,questionList.get(indiceAleatorio).question, questionList.get(indiceAleatorio).typeQuestion);
                                questionBuilderArt(randomArt, value);
                                randomQuestion = (Question) randomArt;

                                break;
                            case ("Ciencia"):
                                ArrayList<Option> Hard = ((Science) questionList.get(indiceAleatorio)).arrayOptionHard;
                                Science randomScience = new Science(questionList.get(indiceAleatorio).arrayOption,questionList.get(indiceAleatorio).question, questionList.get(indiceAleatorio).typeQuestion, Hard );
                                questionBuilderScience(randomScience, value);
                                randomQuestion = (Question) randomScience;

                                break;

                            case("Futebol"):
                                ArrayList<FootballPlayer> playerArray = ((Football) questionList.get(indiceAleatorio)).arrayPlayers;
                                Football randomFootball = new Football(questionList.get(indiceAleatorio).arrayOption,questionList.get(indiceAleatorio).question, playerArray, questionList.get(indiceAleatorio).typeQuestion);
                                questionBuilderFootball(randomFootball, value);
                                randomQuestion = (Question) randomFootball;

                                break;
                            default:
                                randomQuestion = questionList.get(indiceAleatorio);
                                break;
                        }
                         return randomQuestion;                       
                        //questionTypeArray[value] = randomQuestion.typeQuestion;
                        //value++;
                    }
                        
                
            
                } return randomQuestion;
            }
               
             else {
                return null;
                }
            }
            public static Art questionBuilderArt(Art question, int value){
                if (value < 2){
                    int i = 0;
                    while (question.arrayOption.size() > 3){
                           question.arrayOption.remove(question.arrayOption.size()-i);
                           i++;
                    }
                }
                return question;

            }
            public static Football questionBuilderFootball(Football question, int value){
                for (int i = 0; i < question.arrayPlayers.size(); i++){
                    if (value < 2) question.arrayOption.get(i).opString = question.arrayPlayers.get(i).playerName;
                            
                    else question.arrayOption.get(i).opString = question.arrayPlayers.get(i).shirtNumber;
                }
                return question;
            }
            public static Science questionBuilderScience(Science question, int value){
                if (value >= 2){
                    for (int i = 0; i < question.arrayOption.size(); i++){
                        question.arrayOption.set(i, question.arrayOptionHard.get(i));
                    }
                }
                return question;
            }
        


        public static void main(String[] args) {
            ArrayList<Question> questionList = getQuestionsFromFile();
            UiWindow janela = new UiWindow();
            janela.mainWindow();


        }

    }

