package BasesDiarias;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import constant.ExtractorConstants;
import util.Getcreden;

public class ManifestaoSantander {

	public void ConsultaManifestacao() throws IOException {

		String gu = new Getcreden().getCredentials(ExtractorConstants.DATA_USER);
		String gp = new Getcreden().getCredentials(ExtractorConstants.DATA_PSW);

		Date data = new Date();

		String datafile = (new SimpleDateFormat("YYYY_MM_dd")).format(data);

		try {
			Connection con = null;

			Class.forName(ExtractorConstants.JDBC_DRIVER);
			con = DriverManager.getConnection(ExtractorConstants.URL_DB, gu, gp);
			Statement st = con.createStatement();
		

			
//			 File file = new File(
//			 ExtractorConstants.PATCH_SAIDA_LINUX + "/BASE_DIARIA_MANIFESTACAO_SANTANDER_"
//			 + datafile + ".txt");
			
			File file = new File(
					ExtractorConstants.PATCH_SAIDA_WIN + "BASE_DIARIA_MANIFESTACAO_SANTANDER_" + datafile + ".txt");

			PrintWriter pw = new PrintWriter(file);
			BufferedWriter out = new BufferedWriter(pw);
			ResultSet stt = st.executeQuery(
					"SELECT ID_MANIFESTACAO, DS_EMPRESA_MANIFESTACAO, DS_TIPO_MANIFESTACAO, YYYYMM_ABERTURA_MANIFESTACAO, DT_ABERTURA_MANIFESTACAO, TM_ABERTURA_MANIFESTACAO, YYYYMM_CONCLUSAO_MANIFESTACAO, DT_CONCLUSAO_MANIFESTACAO, TM_CONCLUSAO_MANIFESTACAO, DT_PRAZO_CLIENTE, DT_PRAZO_SLA, DS_GRUPO_STATUS_MANIFESTACAO, DS_STATUS_MANIFESTACAO, DS_FAMILIA, DS_PRODUTO, DS_SINTOMA_CURTO, DS_SINTOMA_LONGO, DS_GRUPO_CANAL_ORIGEM, DS_CANAL_ORIGEM, DS_CANAL_OCORRENCIA, DS_AREA_ORIENTADORA, DS_AREA_SOLUCIONADORA, DS_TIPO_CLIENTE, DS_GRUPO_SEGMENTO_CLIENTE, DS_SEGMENTO_CLIENTE, DS_SUBSEGMENTO, DS_TIPO_LOCAL_CLIENTE, CD_LOCAL_CLIENTE, DS_LOCAL_CLIENTE, DS_REGIONAL_LOCAL_CLIENTE, DS_REDE_LOCAL_CLIENTE, DS_TIPO_LOCAL_RECLAMADO, CD_LOCAL_RECLAMADO, DS_LOCAL_RECLAMADO, DS_REGIONAL_LOCAL_RECLAMADO, DS_REDE_LOCAL_RECLAMADO, DS_LOCAL_SOLUCAO, IC_FCR, IC_PROCEDENTE, IC_REINCIDENCIA, IC_CLIENTE_ATENDIDO, CD_PROCESSO_ODC, DS_ODC, DTTM_PRAZO_ODC, DT_ENCERRAMENTO_ODC, DS_STATUS_CONCLUSAO_ODC, QTD_PRAZO_CLIENTE, QTD_PRAZO_SLA, QTD_DIAS_CORRIDOS_PENDENTES, QTD_DIAS_UTEIS_PENDENTES, QTD_TMS, QTD_TMC, DS_STATUS_PRAZO_MANIFESTACAO, CD_ANALISTA_ABERTURA, DS_ANALISTA_ABERTURA, CD_SUPERVISOR_ABERTURA, DS_SUPERVISOR_ABERTURA, DS_CELULA_ABERTURA, CD_ANALISTA_CONCLUSAO, DS_ANALISTA_CONCLUSAO, CD_SUPERVISOR_CONCLUSAO, DS_SUPERVISOR_CONCLUSAO, DS_CELULA_CONCLUSAO, CD_ANALISTA_RESPONSAVEL, DS_ANALISTA_RESPONSAVEL, CD_SUPERVISOR_RESPONSAVEL, DS_SUPERVISOR_RESPONSAVEL, DS_CELULA_RESPONSAVEL, YYYYMM_ULTIMA_ALTERACAO, DT_ULTIMA_ALTERACAO, TM_ULTIMA_ALTERACAO, YYYYMM_REABERTURA, DT_REABERTURA, TM_REABERTURA, DS_DESCRICAO_MANIFESTACAO, DS_ESTADO, DS_CIDADE, IC_REGISTRO_VALIDO, IC_GESTAO_SAC_OUVIDORIA, CD_CONTRATO_FINANCEIRA, DS_TIPO_CONTRATO_FINANCEIRA, DT_OFICIO_ODC, IC_CONTACTOU_OUTROS_CANAIS, CD_LOCAL_ORIGEM, DS_TIPO_LOCAL_ORIGEM, DS_LOCAL_ORIGEM, DS_REDE_LOCAL_ORIGEM, DS_REGIONAL_LOCAL_ORIGEM, IC_PRAZO_SLA_URGENTE, IC_CRITICO_REINCIDENTE, IC_ORGAO_REGULADOR, VLR_ESTORNO_SOLIC, VLR_ESTORNO_EFET, VLR_RESSARCIMENTO_SOLIC, VLR_RESSARCIMENTO_EFET, CD_UNIORG_LOCAL_VINCULO, DS_TIPO_LOCAL_VINCULO, CD_LOCAL_VINCULO, DS_LOCAL_VINCULO, DS_REDE_LOCAL_VINCULO, DS_REGIONAL_LOCAL_VINCULO, CD_UNIORG_LOCAL_ORIGEM, CD_UNIORG_LOCAL_CLIENTE, CD_UNIORG_LOCAL_RECLAMADO, DS_MOTIVO_INSATISFACAO \r\n"
							+ "FROM MOU.TB_MANIFESTACAO_DIAR");


			ResultSetMetaData md = stt.getMetaData();

			String texto = "";

			System.out.println(md.getColumnCount());

			while (stt.next()) {

				for (int i = 1; i <= md.getColumnCount(); i++) {

					String columname = stt.getObject(md.getColumnLabel(i)) == null ? ""
							: stt.getObject(md.getColumnLabel(i)).toString().trim();

					texto += columname + ";";

				}

				out.write(texto + "\n");
				texto = "";

			}
			stt.close();
			out.flush();
			pw.close();
			out.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
