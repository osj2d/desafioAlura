import java.io.PrintWriter;
import java.util.List;


public class HTMLGenerator {
    private PrintWriter writer;
    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }
    public void generate(List<Filme> filmes){
        writer.println(
                """
                        <html lang="pt-br">
                            <head>
                                <meta charset="UTF-8">
                                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Gerador</title>
                                <style>
                                .center {
                                  margin: auto;
                                  width: 30%;
                                  border: 3px solid #000000;
                                  padding: 10px;
                                }
                                </style>
                            </head>
                            <body>""");
        for (Filme filme : filmes){
            String div =
                    """
                       <div class=center>
                           <h4>%s</h4>
                           <img src=%s width="150" height="150">
                           <p>
                                Nota:%s <br>
                                Ano: %s <br>
                           </p>
                       </div>
                            """;
            writer.println(String.format(div, filme.getTitulo(),filme.getUrl(),filme.getNota(), filme.getAno() ));
        };
        writer.println(
                            """
                            </body>
                        </html>
                        """);
    }
}

