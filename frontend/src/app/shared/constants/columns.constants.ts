import { PoTableAction, PoTableColumn } from "@po-ui/ng-components"

export const CLIENT_TABLE_COLUMNS: PoTableColumn[] = [
    { property: 'name', label: 'Name', type: 'string' },
    { property: 'cpf', label: 'CPF', type: 'string' },
  ];

export const getClientTableActions = (
    viewCallback: (client: any) => void,
    editCallback: (client: any) => void,
    deleteCallback: (client: any) => void
    ): PoTableAction[] => [{
        action: viewCallback,
        icon: 'po-icon-eye',
        label: 'Ver Detalhes',
        type: 'default'
    },
    {
        action: editCallback,
        icon: 'po-icon-edit',
        label: 'Editar',
        type: 'default'
    },
    {
        action: deleteCallback,
        icon: 'po-icon-delete',
        label: 'Excluir',
        type: 'danger'
    }
];