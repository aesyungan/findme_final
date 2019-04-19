using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaEntidades
{
    public class DatosJson <T>
    {
       
      
        
        public int success { get; set; }
        public string message { get; set; }
        public List<T> listDatos { get; set; }
        public DatosJson()
        {

        }


    }
}
