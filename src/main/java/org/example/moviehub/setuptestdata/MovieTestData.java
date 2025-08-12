package org.example.moviehub.setuptestdata;

import org.example.moviehub.enums.GenreType;
import org.example.moviehub.model.Genre;
import org.example.moviehub.model.Movie;
import org.example.moviehub.service.GenreService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieTestData {
    public static List<Movie> getSampleMovies(GenreService genreService) {
        Map<GenreType, Genre> genreMap = genreService.findAllGenres().stream().collect(Collectors.toMap(genre -> genre.getGenreType(), genre -> genre));

        return List.of(
                new Movie("Inception", "Ein Dieb, der in die Träume anderer eindringt, um Geheimnisse zu stehlen, bekommt die Chance auf Erlösung.", 8.8, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.THRILLER)), "Christopher Nolan", 2010),
                new Movie("The Shawshank Redemption", "Ein unschuldig verurteilter Mann freundet sich im Gefängnis mit einem Mitgefangenen an und plant seine Flucht.", 9.3, Set.of(genreMap.get(GenreType.DRAMA)), "Frank Darabont", 1994),
                new Movie("The Godfather", "Die Geschichte der Corleone-Familie und ihres Aufstiegs im organisierten Verbrechen.", 9.2, Set.of(genreMap.get(GenreType.CRIME), genreMap.get(GenreType.DRAMA)), "Francis Ford Coppola", 1972),
                new Movie("Interstellar", "Eine Gruppe von Forschern reist durch ein Wurmloch, um das Überleben der Menschheit zu sichern.", 8.6, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.ADVENTURE)), "Christopher Nolan", 2014),
                new Movie("Parasite", "Eine arme Familie schleicht sich in das Leben einer reichen Familie ein – mit unerwarteten Folgen.", 8.6, Set.of(genreMap.get(GenreType.THRILLER), genreMap.get(GenreType.DRAMA)), "Bong Joon-ho", 2019),
                new Movie("Pulp Fiction", "Mehrere miteinander verwobene Geschichten aus der Unterwelt von Los Angeles.", 8.9, Set.of(genreMap.get(GenreType.CRIME), genreMap.get(GenreType.DRAMA)), "Quentin Tarantino", 1994),
                new Movie("The Dark Knight", "Batman stellt sich dem Joker, einem kriminellen Genie, das Gotham ins Chaos stürzen will.", 9.0, Set.of(genreMap.get(GenreType.ACTION), genreMap.get(GenreType.CRIME), genreMap.get(GenreType.DRAMA)), "Christopher Nolan", 2008),
                new Movie("Fight Club", "Ein unzufriedener Büroangestellter gründet mit einem Seifenverkäufer einen Untergrund-Fight Club.", 8.8, Set.of(genreMap.get(GenreType.DRAMA)), "David Fincher", 1999),
                new Movie("Forrest Gump", "Die außergewöhnliche Lebensgeschichte eines einfachen Mannes mit einem großen Herzen.", 8.8, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.ROMANCE)), "Robert Zemeckis", 1994),
                new Movie("The Matrix", "Ein Hacker entdeckt die wahre Natur seiner Realität und schließt sich dem Widerstand an.", 8.7, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.ACTION)), "Lana Wachowski, Lilly Wachowski", 1999),
                new Movie("Gladiator", "Ein römischer General wird verraten, verliert seine Familie und kämpft als Gladiator um Rache.", 8.5, Set.of(genreMap.get(GenreType.ACTION), genreMap.get(GenreType.DRAMA)), "Ridley Scott", 2000),
                new Movie("The Lord of the Rings: The Fellowship of the Ring", "Ein Hobbit begibt sich auf eine gefährliche Reise, um einen mächtigen Ring zu zerstören.", 8.8, Set.of(genreMap.get(GenreType.FANTASY), genreMap.get(GenreType.ADVENTURE)), "Peter Jackson", 2001),
                new Movie("Titanic", "Eine Liebesgeschichte vor dem Hintergrund der tragischen Jungfernfahrt der Titanic.", 7.9, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.ROMANCE)), "James Cameron", 1997),
                new Movie("The Silence of the Lambs", "Eine junge FBI-Agentin sucht die Hilfe eines inhaftierten Serienmörders, um einen anderen zu fassen.", 8.6, Set.of(genreMap.get(GenreType.THRILLER), genreMap.get(GenreType.CRIME)), "Jonathan Demme", 1991),
                new Movie("Jurassic Park", "Ein Freizeitpark mit geklonten Dinosauriern gerät außer Kontrolle.", 8.1, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.ADVENTURE)), "Steven Spielberg", 1993),
                new Movie("The Lion King", "Ein junger Löwe muss seinen Platz als König einnehmen, nachdem sein Vater getötet wurde.", 8.5, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.DRAMA)), "Roger Allers, Rob Minkoff", 1994),
                new Movie("Schindler's List", "Die wahre Geschichte eines Mannes, der während des Holocausts hunderte Juden rettete.", 9.0, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.WAR)), "Steven Spielberg", 1993),
                new Movie("Back to the Future", "Ein Teenager reist versehentlich 30 Jahre in die Vergangenheit und muss sicherstellen, dass seine Eltern sich verlieben.", 8.5, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.COMEDY)), "Robert Zemeckis", 1985),
                new Movie("The Green Mile", "Ein Gefängniswärter erlebt wundersame Ereignisse, nachdem ein neuer Häftling eintrifft.", 8.6, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.FANTASY)), "Frank Darabont", 1999),
                new Movie("The Prestige", "Zwei rivalisierende Magier führen ein tödliches Duell um den ultimativen Trick.", 8.5, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.MYSTERY)), "Christopher Nolan", 2006),
                new Movie("Saving Private Ryan", "Eine Gruppe von Soldaten wird im Zweiten Weltkrieg ausgeschickt, um einen vermissten Kameraden zu finden.", 8.6, Set.of(genreMap.get(GenreType.WAR), genreMap.get(GenreType.DRAMA)), "Steven Spielberg", 1998),
                new Movie("La La Land", "Ein Jazzmusiker und eine Schauspielerin verlieben sich in Los Angeles, während sie ihre Träume verfolgen.", 8.0, Set.of(genreMap.get(GenreType.ROMANCE), genreMap.get(GenreType.MUSICAL)), "Damien Chazelle", 2016),
                new Movie("Coco", "Ein Junge begibt sich ins Land der Toten, um seine Familie zu finden.", 8.4, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.FANTASY)), "Lee Unkrich, Adrian Molina", 2017),
                new Movie("Braveheart", "Die Geschichte von William Wallace und dem schottischen Unabhängigkeitskrieg.", 8.3, Set.of(genreMap.get(GenreType.DRAMA), genreMap.get(GenreType.WAR)), "Mel Gibson", 1995),
                new Movie("The Departed", "Ein Undercover-Polizist und ein Maulwurf in der Polizei versuchen, sich gegenseitig zu entlarven.", 8.5, Set.of(genreMap.get(GenreType.CRIME), genreMap.get(GenreType.THRILLER)), "Martin Scorsese", 2006),
                new Movie("The Wolf of Wall Street", "Die wahre Geschichte eines Börsenmaklers, der durch Betrug reich wird.", 8.2, Set.of(genreMap.get(GenreType.COMEDY), genreMap.get(GenreType.CRIME)), "Martin Scorsese", 2013),
                new Movie("Django Unchained", "Ein befreiter Sklave schließt sich einem Kopfgeldjäger an, um seine Frau zu retten.", 8.4, Set.of(genreMap.get(GenreType.WESTERN), genreMap.get(GenreType.DRAMA)), "Quentin Tarantino", 2012),
                new Movie("Avengers: Endgame", "Die Avengers müssen die Hälfte des Universums zurückbringen, nachdem Thanos es zerstört hat.", 8.4, Set.of(genreMap.get(GenreType.ACTION), genreMap.get(GenreType.SCIENCE_FICTION)), "Anthony Russo, Joe Russo", 2019),
                new Movie("Guardians of the Galaxy", "Eine Gruppe von Außenseitern muss das Universum retten.", 8.0, Set.of(genreMap.get(GenreType.SCIENCE_FICTION), genreMap.get(GenreType.COMEDY)), "James Gunn", 2014),
                new Movie("Frozen", "Zwei Schwestern müssen ihr Königreich retten, das in ewigen Winter gestürzt wurde.", 7.4, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.MUSICAL)), "Chris Buck, Jennifer Lee", 2013),
                new Movie("Finding Nemo", "Ein Clownfisch sucht seinen verlorenen Sohn im Ozean.", 8.1, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ADVENTURE)), "Andrew Stanton", 2003),
                new Movie("Up", "Ein alter Mann reist mit einem fliegenden Haus, um den Traum seiner verstorbenen Frau zu erfüllen.", 8.3, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ADVENTURE)), "Pete Docter, Bob Peterson", 2009),
                new Movie("Toy Story", "Spielzeuge erleben Abenteuer, wenn Menschen nicht hinsehen.", 8.3, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.COMEDY)), "John Lasseter", 1995),
                new Movie("WALL·E", "Ein kleiner Roboter versucht, die Erde zu retten.", 8.4, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.SCIENCE_FICTION)), "Andrew Stanton", 2008),
                new Movie("Inside Out", "Die Emotionen eines jungen Mädchens müssen zusammenarbeiten, um sie durch eine schwierige Zeit zu bringen.", 8.1, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.COMEDY)), "Pete Docter", 2015),
                new Movie("Ratatouille", "Eine Ratte möchte in Paris Koch werden.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.COMEDY)), "Brad Bird", 2007),
                new Movie("Shrek", "Ein Oger begibt sich auf eine Reise, um eine Prinzessin zu retten.", 7.9, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.COMEDY)), "Andrew Adamson, Vicky Jenson", 2001),
                new Movie("Monsters, Inc.", "Monster erschrecken Kinder, um Energie zu gewinnen, bis ein kleines Mädchen ihre Welt betritt.", 8.1, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.COMEDY)), "Pete Docter, David Silverman, Lee Unkrich", 2001),
                new Movie("Zootopia", "Eine Hasenpolizistin und ein Fuchs decken eine Verschwörung in ihrer Stadt auf.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.CRIME)), "Byron Howard, Rich Moore", 2016),
                new Movie("Moana", "Ein Mädchen segelt, um ihre Insel zu retten.", 7.6, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ADVENTURE)), "Ron Clements, John Musker", 2016),
                new Movie("Aladdin", "Ein armer Junge findet eine magische Lampe.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ROMANCE)), "Ron Clements, John Musker", 1992),
                new Movie("Beauty and the Beast", "Eine junge Frau verliebt sich in ein verzaubertes Biest.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ROMANCE)), "Gary Trousdale, Kirk Wise", 1991),
                new Movie("The Incredibles", "Eine Familie von Superhelden versucht, ein normales Leben zu führen.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.ACTION)), "Brad Bird", 2004),
                new Movie("The Iron Giant", "Ein Junge freundet sich mit einem riesigen Roboter an.", 8.0, Set.of(genreMap.get(GenreType.ANIMATION), genreMap.get(GenreType.SCIENCE_FICTION)), "Brad Bird", 1999)
        );
    }
    public static List<Genre> getGenres() {
        GenreType[] values = GenreType.values();
        return Arrays.stream(values).map(type -> new Genre(type)).collect(Collectors.toList());
    }
}
