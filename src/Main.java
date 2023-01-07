import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws Exception {
        String apikey = "k_zwrqbxh4"; //Cola a apiKey da conta do imdb-api
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apikey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        String[] moviesArray = parseJsonMovies(json);

        //List<String> titles = parseTitles(moviesArray);
        //titles.forEach(System.out::println);

       //List<String> urlImages = parseUrlImages(moviesArray);
        //urlImages.forEach(System.out::println);

        //List<String> year = parseYear(moviesArray);
        //year.forEach(System.out::println);

        //List<String> imbRankin = parseRating(moviesArray);
        //imbRankin.forEach(System.out::println);

        List<Filme> filmes = parseMovies(json);
        filmes.forEach(System.out::println);

    }
        private static String[] parseJsonMovies(String json){
            Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

            if(!matcher.matches()){
                throw new IllegalArgumentException("No match in" + json);
            }
            String[] moviesArray = matcher.group(1).split("\\},\\{");
            moviesArray[0] = moviesArray[0].substring(1);
            int last = moviesArray.length -1;
            String lastString = moviesArray[last];
            moviesArray[last] = lastString.substring(0, lastString.length() - 1);
            return moviesArray;
        }
        private static List<String> parseAttribute(String[] moviesArray, int pos){
            return Stream.of(moviesArray)
                    .map(e -> e.split("\",\"")[pos])
                    .map(e -> e.split(":\"")[1])
                    .map(e -> e.replaceAll("\"", ""))
                    .collect(Collectors.toList());
        }
        private static List<String> parseTitles(String[] moviesArray){
            return parseAttribute(moviesArray, 2);
        }
        private static List<String> parseUrlImages(String[] moviesArray){
            return parseAttribute(moviesArray, 5);
        }
        private static List<String> parseYear(String[] moviesArray){
            return parseAttribute(moviesArray, 4);
        }
        private static List<String> parseRating(String[] moviesArray){
            return parseAttribute(moviesArray, 7);
        }

        private static List<Filme> parseMovies(String json){
            String[] moviesArray = parseJsonMovies(json);

            List<String> titles = parseTitles(moviesArray);
            List<String> urlImages = parseUrlImages(moviesArray);
            List<String> imbRankin = parseRating(moviesArray);
            List<String> year = parseYear(moviesArray);

            List<Filme> filmes = new ArrayList<>(titles.size());

            for (int i = 0; i < titles.size(); i++) {
                filmes.add( new Filme(titles.get(i), urlImages.get(i), imbRankin.get(i), year.get(i)));
            }
            return filmes;
    }

}
