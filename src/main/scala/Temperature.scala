class Temperature(val kelvin : Double) {
  def toCelsius : Double = {
    val x = kelvin - 273.15;
    return "%.2f".format(x).toDouble
  }

  def toFahrenheit : Double = {
    val x = ( ( kelvin - 273.15 ) * 1.8 ) + 32
    return "%.2f".format(x).toDouble
  }
}
