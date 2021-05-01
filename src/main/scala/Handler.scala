
import model.Book

import java.io.FileReader
import java.util
import java.util.stream.Collectors


object Handler {

  def getData: util.List[Book] = {
    val in = new FileReader("src/main/resources/data.csv");
    import org.apache.commons.csv.CSVFormat
    val records = CSVFormat.DEFAULT.withHeader().withFirstRecordAsHeader.parse(in)

     records
      .getRecords
      .stream()
      .map(el => Book(el.get(0), el.get(1), el.get(2), el.get(3), el.get(4), el.get(5), el.get(6)))
      .collect(Collectors.toList[Book])
  }

}
