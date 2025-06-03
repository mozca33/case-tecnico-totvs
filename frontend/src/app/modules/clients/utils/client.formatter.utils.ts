export function formatClientName(client: any): string {
  return client && client.name ? client.name.toUpperCase() : '';
}