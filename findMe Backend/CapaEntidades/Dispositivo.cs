using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaEntidades
{
   public class Dispositivo
    {
        public int id_dispositivo { get; set; }
        public string descripcion { get; set; }
        public string imei { get; set; }
        public DateTime fecha_update_registro { get; set; }
        public double latitud { get; set; }
        public double longitud { get; set; }
        public double altura { get; set; }
        public DateTime fechaupdate { get; set; }
    }
}
