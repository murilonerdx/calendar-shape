import '@vaadin/vaadin-button';
import '@vaadin/vaadin-form-layout';
import { customElement, html, LitElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-date-picker';
import '@vaadin/vaadin-text-field';

@customElement('criardata-view')
export class CriardataView extends LitElement {
  createRenderRoot() {
    // Do not use a shadow root
    return this;
  }

  render() {
    return html`
<h3 id="informationEvent">Informação do evento</h3>
<vaadin-form-layout style="width: 100%;">
 <vaadin-text-field label="Nome do evento" id="nameEvent" required invalid></vaadin-text-field>
 <vaadin-text-field label="Descrição do evento" id="descriptionEvent" required></vaadin-text-field>
 <vaadin-date-picker id="dateEvent" label="Data do evento" required invalid></vaadin-date-picker>
 <vaadin-combo-box id="importEvent" required allow-custom-value value="Evento"></vaadin-combo-box>
 <vaadin-text-field id="typeEvent" label="Tipo do evento" required></vaadin-text-field>
</vaadin-form-layout>
<vaadin-horizontal-layout style="margin-top: var(--lumo-space-m); margin-bottom: var(--lumo-space-l);" theme="spacing">
 <vaadin-button theme="primary" id="save">
   Salvar 
 </vaadin-button>
 <vaadin-button id="cancel">
   Cancelar 
 </vaadin-button>
</vaadin-horizontal-layout>
`;
  }
}
