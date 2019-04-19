using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CapaEntidades;
using System.Data;
using System.Data.SqlClient;

namespace CapaAccesoAtos
{
   public class DispositivoAD
    {

        #region "PATRON SINGLETON"
        private static DispositivoAD dispositivoAD = null;
        public DispositivoAD()
        {

        }
        public static DispositivoAD getInstance()
        {
            if (dispositivoAD == null)
            {
                dispositivoAD = new DispositivoAD();
            }
            return dispositivoAD;
        }
        #endregion

        public List<Dispositivo> Listar()
        {
            List<Dispositivo> listUsuarioreturn = new List<Dispositivo>();

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;

            try
            {
                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_mostrar_dispositivo, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                //abrimos Coneccion
                coneccion.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())//si existe datos
                {
                    Dispositivo usuarioreturn = new Dispositivo();
                    
                    usuarioreturn.id_dispositivo = Convert.ToInt32(reader[0].ToString());
                    usuarioreturn.descripcion = (reader[1].ToString());
                    usuarioreturn.imei = reader["imei"].ToString();
                    usuarioreturn.fecha_update_registro = Convert.ToDateTime( reader["fecha_update_registro"].ToString());
                    usuarioreturn.latitud = Convert.ToDouble( reader["latitud"].ToString());
                    usuarioreturn.longitud = Convert.ToDouble(reader["longitud"].ToString());
                    usuarioreturn.altura = Convert.ToDouble(reader["altura"].ToString());
                    usuarioreturn.fechaupdate = Convert.ToDateTime(reader["fechaupdate"].ToString());

                    listUsuarioreturn.Add(usuarioreturn);

                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return listUsuarioreturn;
        }

        //insertar

        public int Ingreso(Dispositivo item)
        {
       
            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_insertar_dispositivo, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.AddWithValue("@descripcion", item.descripcion);
                cmd.Parameters.AddWithValue("@imei", item.imei);
                cmd.Parameters.AddWithValue("@latitud", item.latitud);
                cmd.Parameters.AddWithValue("@longitud", item.longitud);
                cmd.Parameters.AddWithValue("@altura", item.altura);


                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }
        //modificar
        public int Update(Dispositivo item)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_update_dispositivo, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@id_dispositivo", item.id_dispositivo);
                cmd.Parameters.AddWithValue("@descripcion", item.descripcion);
                cmd.Parameters.AddWithValue("@imei", item.imei);
                cmd.Parameters.AddWithValue("@fecha_update_registro", item.fecha_update_registro);
                cmd.Parameters.AddWithValue("@latitud", item.latitud);
                cmd.Parameters.AddWithValue("@longitud", item.longitud);
                cmd.Parameters.AddWithValue("@altura", item.altura);


                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }

        public int Delete(Dispositivo item)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_delete_dispositivo, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@id_dispositivo", item.id_dispositivo);
                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }


    }
}
