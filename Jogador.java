import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Jogador {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner entrada = new Scanner(System.in);
		Socket conexao = new Socket("127.0.0.1", 9843);

        String opcao_Jogador = "";
        String opcao_Servidor = "";

        System.out.println("============= SEJA BEM VINDO AO JOGO JOQUEMPÔ ==============");
        System.out.println("\nREGRAS: ");
        System.out.println("1 - Você jogará contra a CPU;");
        System.out.println("2 - Você deve escolher entre PEDRA, PAPEL E TESOURA;");
        System.out.println("3 - Serão realizadas 5 rodadas;");  
        System.out.println("4 - Cada rodada vencida vale 1 ponto;");
        System.out.println("5 - Vence o jogo quem obter mais pontos nas 5 rodadas;");
        
        
        System.out.println("\nCOMEÇANDO O JOGO...");

        boolean venceu = false;
        while(!venceu) {
            for (int index = 0; index < 5; index++) {
            System.out.println("\n============================================================\nEscolha uma opção: ");
            System.out.println("1 - PEDRA");
            System.out.println("2 - PAPEL");
            System.out.println("3 - TESOURA");
            System.out.print("OPÇÃO >>> ");
            //Enviar o palpite para o Servidor
            opcao_Jogador = entrada.nextLine();
            byte[]dados2 = opcao_Jogador.getBytes();
            conexao.getOutputStream().write(dados2);
            conexao.getOutputStream().flush();
            //Receber o feedback do Servidor
			byte[] dados3 = new byte[120];
			conexao.getInputStream().read(dados3);
			String mensagem2 = new String(dados3);
			System.out.println(mensagem2);
            
            System.out.println("\nSortear opcão do servidor? (sim) ou (não)");
            opcao_Servidor = entrada.nextLine();
            //Enviar condição para o Servidor
            byte[]dados = opcao_Servidor.getBytes();
            conexao.getOutputStream().write(dados);
            conexao.getOutputStream().flush();
            //Receber o feedback do Servidor
			byte[] dados1 = new byte[120];
			conexao.getInputStream().read(dados1);
			String mensagem = new String(dados1);
			System.out.println(mensagem);

            //Receber o feedback do Servidor
            byte[] dados4 = new byte[120];
			conexao.getInputStream().read(dados4);
			String mensagem3 = new String(dados4);
			System.out.println(mensagem3);
            }

            //Receber o feedback do Servidor
            byte[] dados5 = new byte[100];
			conexao.getInputStream().read(dados5);
			String mensagem4 = new String(dados5);
			System.out.println(mensagem4); 
            if (mensagem4.contains(mensagem4)) {
                venceu = true; 
            }
        }
    }
}
