package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PaginaPrincipal implements Initializable{


	
	@FXML
	private Pane painelPrincipal;
	@FXML
	private Image editar;



@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}


// Metodos da parte de cadastro
	
public void cadUsuario(){
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/View/CadastroUsuario.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cadEquipamento(){
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/View/CadastroEquipamento.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		public void cadAuditorio(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroAuditorio.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public void cadLaboratorio(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroLaboratorio.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public void cadSala(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroSala.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public void cadAutoridade(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroAutoridade.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void cadBloco(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroBloco.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		public void cadUnidade(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CadastroNovasUnidades.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void kits(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CriarKit.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		//Metodos da parte de reserva
		
		public void reservaCancelar(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CancelarReserva.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void cancelarReservaADM(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/CancelarReservaAdm.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
			public void reservarAuditorio(){
				URL arquivoFXML;
				arquivoFXML = getClass().getResource("/View/ReservarAuditorio.fxml");
				Parent fxmlParent;
				try {
					fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

					painelPrincipal.getChildren().add(fxmlParent);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
				public void reservarEquipamento(){
					URL arquivoFXML;
					arquivoFXML = getClass().getResource("/View/ReservarEquipamento.fxml");
					Parent fxmlParent;
					try {
						fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

						painelPrincipal.getChildren().add(fxmlParent);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
					public void reservaLaboratorio(){
						URL arquivoFXML;
						arquivoFXML = getClass().getResource("/View/ReservarLaboratorio.fxml");
						Parent fxmlParent;
						try {
							fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

							painelPrincipal.getChildren().add(fxmlParent);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
						public void reservaSala(){
							URL arquivoFXML;
							arquivoFXML = getClass().getResource("/View/ReservarSala.fxml");
							Parent fxmlParent;
							try {
								fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

								painelPrincipal.getChildren().add(fxmlParent);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					
						
						
						// Metodos da parte de alterar os cadastros
						
						public void alterarUsuario(){
							URL arquivoFXML;
							arquivoFXML = getClass().getResource("/View/CadastroUsuario.fxml");
							Parent fxmlParent;
							try {
								fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

								painelPrincipal.getChildren().add(fxmlParent);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}


							
							public void alterarEquipamento(){
								URL arquivoFXML;
								arquivoFXML = getClass().getResource("/View/ReservarLaboratorio.fxml");
								Parent fxmlParent;
								try {
									fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

									painelPrincipal.getChildren().add(fxmlParent);
								} catch (IOException e) {
									e.printStackTrace();
								}
	
		}

								public void alterarSala(){
									URL arquivoFXML;
									arquivoFXML = getClass().getResource("/View/CadastroUsuario.fxml");
									Parent fxmlParent;
									try {
										fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
										painelPrincipal.getChildren().clear();
										painelPrincipal.getChildren().add(fxmlParent);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								

												
												//Metodos de Relatorios
											
												public void relatorioCadastro(){
													URL arquivoFXML;
													arquivoFXML = getClass().getResource("/View/RelatorioCadastro.fxml");
													Parent fxmlParent;
													try {
														fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
														painelPrincipal.getChildren().clear();
														painelPrincipal.getChildren().add(fxmlParent);
													} catch (IOException e) {
														e.printStackTrace();
													}
						
							}
												public void relatorioReserva(){
													URL arquivoFXML;
													arquivoFXML = getClass().getResource("/View/RelatorioReserva.fxml");
													Parent fxmlParent;
													try {
														fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
														painelPrincipal.getChildren().clear();
														painelPrincipal.getChildren().add(fxmlParent);
													} catch (IOException e) {
														e.printStackTrace();
													}
						
							}
												public void relatorioReservaADM(){
													URL arquivoFXML;
													arquivoFXML = getClass().getResource("/View/RelatorioReservaADM.fxml");
													Parent fxmlParent;
													try {
														fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
														painelPrincipal.getChildren().clear();
														painelPrincipal.getChildren().add(fxmlParent);
													} catch (IOException e) {
														e.printStackTrace();
													}
						
							}
												
												public void relatorioCancelamento(){
													URL arquivoFXML;
													arquivoFXML = getClass().getResource("/View/RelatorioCancelamento.fxml");
													Parent fxmlParent;
													try {
														fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
														painelPrincipal.getChildren().clear();
														painelPrincipal.getChildren().add(fxmlParent);
													} catch (IOException e) {
														e.printStackTrace();
													}
						
							}


    public void pesquisarEquipamento(ActionEvent actionEvent) {
    }

	public void pesquisarSala(ActionEvent actionEvent) {
	}

	public void pesquisarUsuario(ActionEvent actionEvent) {
	}

	public void pesquisarUnidade(ActionEvent actionEvent) {
	}

	public void editarUnidade(ActionEvent actionEvent) {


		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/View/EditarUnidade.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void excluirUnidade(ActionEvent actionEvent) {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/View/ExcluirUnidade.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	}

					
			
