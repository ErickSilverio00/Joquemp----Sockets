import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        //Criar o ServerSocket para escutar a porta 6789
		ServerSocket servidor = new ServerSocket(9843);
		//Aguardar pela conexão do cliente e aceitá-la quando ocorrer
		Socket conexao = servidor.accept();
        System.out.println("Jogador conectado!");

        int pontos_Servidor = 0;
        int pontos_Jogador = 0;

        boolean venceu = false;
        while (!venceu) {
            for (int i = 0; i < 5; i++) { 
            int opcaoServidor = (int) (Math.random() * 3 + 1);
            byte[]dados2 = new byte [40];
            conexao.getInputStream().read(dados2);
            String opcao_Jogador = new String (dados2);

            if(opcao_Jogador.contains("1")) {
                String mensagem2 = "\nJOGADOR ESCOLHEU PEDRA!";
                byte[] dados3 = mensagem2.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados3);
                conexao.getOutputStream().flush();
            }

            else if(opcao_Jogador.contains("2")) {
                String mensagem2 = "\nJOGADOR ESCOLHEU PAPEL!";
                byte[] dados3 = mensagem2.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados3);
                conexao.getOutputStream().flush();
            }

            else if(opcao_Jogador.contains("3")) {
                String mensagem2 = "\nJOGADOR ESCOLHEU TESOURA!";
                byte[] dados3 = mensagem2.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados3);
                conexao.getOutputStream().flush();
            }

            else {
                String mensagem2 = "\nOPÇÃO INVÁLIDA!";
                byte[] dados3 = mensagem2.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados3);
                conexao.getOutputStream().flush();
            }

            byte[]dados = new byte [40];
            conexao.getInputStream().read(dados);
            String opcao_Servidor = new String (dados);
            
            if(opcao_Servidor.contains("sim") && opcaoServidor == 1) {
                String mensagem = "\nSERVIDOR ESCOLHEU PEDRA!";
                byte[] dados1 = mensagem.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados1);
                conexao.getOutputStream().flush();
            }

            else if(opcao_Servidor.contains("sim") && opcaoServidor == 2) {
                String mensagem = "\nSERVIDOR ESCOLHEU PAPEL!";
                byte[] dados1 = mensagem.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados1);
                conexao.getOutputStream().flush();
            }

            else if(opcao_Servidor.contains("sim") && opcaoServidor == 3) {
                String mensagem = "\nSERVIDOR ESCOLHEU TESOURA!";
                byte[] dados1 = mensagem.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados1);
                conexao.getOutputStream().flush();
            }

            else {
                String mensagem = "\nOPÇÃO INVÁLIDA!";
                byte[] dados1 = mensagem.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados1);
                conexao.getOutputStream().flush();
            }

            if (opcaoServidor == 1 && opcao_Jogador.contains("1")  || opcaoServidor == 2 && opcao_Jogador.contains("2")
            || opcaoServidor == 3 && opcao_Jogador.contains("3"))  {
                String mensagem3 = "\nEMPATE NA RODADA!\n============================================================";
                byte[] dados4 = mensagem3.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados4);
                conexao.getOutputStream().flush();
            }

            else if (opcaoServidor == 1 && opcao_Jogador.contains("3") || opcaoServidor == 2 && opcao_Jogador.contains("1")
            || opcaoServidor == 3 && opcao_Jogador.contains("2")){
                pontos_Servidor++;
                String mensagem3 = "\nSERVIDOR VENCEU A RODADA!\n============================================================";
                byte[] dados4 = mensagem3.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados4);
                conexao.getOutputStream().flush();
            }

            else if (opcaoServidor == 3 && opcao_Jogador.contains("1") || opcaoServidor == 1 && opcao_Jogador.contains("2")
            || opcaoServidor == 2 && opcao_Jogador.contains("3")){
                pontos_Jogador++;
                String mensagem3 = "\nJOGADOR VENCEU A RODADA!\n============================================================";
                byte[] dados4 = mensagem3.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados4);
                conexao.getOutputStream().flush();    
            }

            else {
                String mensagem3 = "\nRESULTADO INVÁLIDO NA RODADA!\n============================================================";
                byte[] dados4 = mensagem3.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados4);
                conexao.getOutputStream().flush();    
            }
        }

            if (pontos_Jogador > pontos_Servidor) {
                String mensagem4 = "\nJOGADOR VENCEU O JOGO!";
                byte[] dados5 = mensagem4.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados5);
                conexao.getOutputStream().flush();
                venceu = true;
            }

            else if (pontos_Servidor > pontos_Jogador) {
                String mensagem4 = "\nSERVIDOR VENCEU O JOGO!";
                byte[] dados5 = mensagem4.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados5);
                conexao.getOutputStream().flush();
                venceu = true;
            }

            else {
                String mensagem4 = "\nEMPATE ENTRE JOGADOR E SERVIDOR!";
                byte[] dados5 = mensagem4.getBytes();
                //Enviar a mensagem para o usuário
                conexao.getOutputStream().write(dados5);
                conexao.getOutputStream().flush();
                venceu = true;
            }
        }
        conexao.close();
    }
}